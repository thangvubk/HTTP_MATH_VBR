package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Client.Buffer;
import Client.Manifest;

public class TrellisHTTP2 extends AdaptationLogic {
	int isHungModify = 1;
	int nextBitrate, nextN, curBitrate=0, tarBitrate=0;
	double margin = 0.9;
	int maxN = 4; 
	int noOfN = 4;
	int pathLenth = 6;
	int curIndex = 0;
	int Bmin = 2000;
	ArrayList<Selection> bestPath;
	ArrayList<Double> buffFlowBestPath;
	Selection[] setPoint = new Selection[manifest.NUMBER_OF_BITRATES*noOfN];
	ArrayList<ArrayList<Selection>> trellis;
	int BUFFER_FLUCTUATION = 2000;
	int[] pushArray = new int[]{1000, 2000, 3000, 4000};
	AdaptationDecision adaptationDecision;
	public TrellisHTTP2(Buffer buffer, Manifest manifest) {
		super(buffer, manifest);
		//init setPoint-------------------------------
		int count = 0;
		for (int j = manifest.NUMBER_OF_BITRATES-1; j >= 0; j--) {
		//for(int j = 0; j<manifest.NUMBER_OF_BITRATES;j++){
			for (int i = 0; i < noOfN; i++)
				setPoint[count++] = new Selection(manifest.bitrateArray[j], (int) (pushArray[i]/1000));
		}
		System.out.println("check1");
		trellis = buildTrellis(setPoint, 3);
	}
	

	@Override
	protected AdaptationDecision decideNextRequest() {
		
		curBitrate = currentParams.bitrate;
		setEstimatedNextThroughput(ESTIMATE_SMOOTH_THROUGHPUT);
		if (overallThroughput < curBitrate) {
			// search to find out the new path for the next throughput change.
			if (bestPath == null|| Math.abs(buffer.getBufferSize() - buffFlowBestPath.get(curIndex)) > BUFFER_FLUCTUATION) {
				tarBitrate = manifest.selectFloorBitrate(overallThroughput*margin);
				getBestPath(curBitrate, tarBitrate);
				buffFlowBestPath = callBuffFlowPath(bestPath);
				//System.out.println(bestPath.toString());
				//System.out.println(buffFlowBestPath.toString());
				curIndex = 0; 
			}
	
			// if don't complete a way, just select the next bitrate
			if (bestPath != null && curIndex < bestPath.size()) { 
				//System.out.println("Current Index: " + curIndex);
				nextBitrate = bestPath.get(curIndex).bitrate;
				nextN = bestPath.get(curIndex).numOfSegment;
				curIndex++;
			// if complete a way, bandwidth stable
			} else {
				tarBitrate = manifest.selectFloorBitrate(overallThroughput*margin);
				getBestPath(curBitrate, tarBitrate);
				buffFlowBestPath = callBuffFlowPath(bestPath);
				curIndex = 0; 
				nextBitrate = bestPath.get(curIndex).bitrate;
				nextN = bestPath.get(curIndex).numOfSegment;
				curIndex++;
				System.out.println("beginBitrate " + curBitrate + " Target " + tarBitrate);
			}
			for (int i = 0; i < bestPath.size(); i++) {
				System.out.print(bestPath.get(i).bitrate );							
			}
			System.out.println();
			for (int i = 0; i < bestPath.size(); i++) {
				System.out.print(bestPath.get(i).numOfSegment );							
			}
			System.out.println();
			
			System.out.println("Cur Bitrate " + curBitrate + " current Index " + curIndex);
		}else{
			bestPath = null;
			if (buffer.getBufferSize() < buffer.bufOptThreshold) {
				nextBitrate = curBitrate;
				if(isHungModify == 1){
					nextN = 1;
					double tempBuf = buffer.getBufferSize(); 
					while(tempBuf < buffer.getOptimumThreshold()){
						nextN ++;
						tempBuf = buffer.getBufferSize() + nextN*manifest.REAL_SEGMENT_DURATION*(1-(double)curBitrate/overallThroughput);
					}
					if(nextN > maxN){
						nextN = maxN;
					}
				}
			} else {
				nextBitrate = manifest.selectFloorBitrate(estimatedNextThroughput);
				if(isHungModify == 1){
					nextN = maxN;
				}
			}
			if(isHungModify == 0){
				nextN = (int) (pushArray[maxN-1]/manifest.REAL_SEGMENT_DURATION);
				while (nextN > 1){
					if((buffer.getBufferSize() - (float)nextN*manifest.REAL_SEGMENT_DURATION*nextBitrate/estimatedNextThroughput/alpha) >= Bmin){
						break;
					}
					nextN--;
				}
			}
	        		
		}
		
		
		adaptationDecision = new AdaptationDecision(nextBitrate, nextN);
		
    	//System.out.println("no RQ: "+numberOfRequest);
    	return adaptationDecision;
	}

	@Override
	protected AdaptationDecision decideFirstRequest() {
		return new AdaptationDecision(3000, 1);
	}

	private ArrayList<ArrayList<Selection>> buildTrellis(Selection[] setPoint, int n) {
		if (n == 1) {
			ArrayList<ArrayList<Selection>> trellis = new ArrayList<ArrayList<Selection>>();
			for (int i = 0; i < setPoint.length; i++)
				if (true) {
					ArrayList<Selection> path = new ArrayList<Selection>();
					path.add(setPoint[i]);
					trellis.add(path);
				}
			return trellis;
		} else {
			ArrayList<ArrayList<Selection>> trellis = buildTrellis(setPoint, n - 1);
			ArrayList<ArrayList<Selection>> tmp_trellis = new ArrayList<ArrayList<Selection>>();

			for (int i = 0; i < trellis.size(); i++) {
				ArrayList<Selection> path = trellis.get(i);
				double cur_aggre_bw = get_acc_bandwidth(path.toArray(new Selection[path.size()]));
				for (int j = 0; j < setPoint.length; j++)				
				{
					ArrayList<Selection> tmp_path = (ArrayList<Selection>) path.clone();
					tmp_path.add(setPoint[j]);

					if (tmp_trellis.size() == 0)
						tmp_trellis.add(tmp_path);
					else {
						tmp_trellis.add(0,tmp_path);
					}
				}
			}
			// System.out.println(tmp_trellis.size() + "");
			return tmp_trellis;
		}
	}

	double get_acc_bandwidth(Selection[] path) {
		double sum = 0;
		for (int i = 0; i < path.length; i++)
			sum += path[i].bitrate;
		return sum;
	}

	double getOverallCost(Selection[] path, int curBitrate, int tarBitrate) {
		double alpha = 0.5, beta = 25, gamma = 13.5, sigma = 10, lamda = 0.25;
		double costRequest, costBuffer, costQuality;
		double averageN = 0, totalUsedBuffer = 0, maxChangeQ = 0;
		if (path[path.length - 1].bitrate != tarBitrate){
			//System.err.println("aaa");
			return 100000000;
		}
		else {
			for (int i = 0; i < path.length; i++) {
			
				averageN += (double) path[i].numOfSegment / path.length;
				totalUsedBuffer += (double) path[i].numOfSegment * Manifest.REAL_SEGMENT_DURATION / 1000
						* (1 - (double) path[i].bitrate / overallThroughput);
				int curVersion;
				if(i==0){
					curVersion = manifest.getIndexbyBitrate(curBitrate);
				}else{
					curVersion = manifest.getIndexbyBitrate(path[i - 1].bitrate);
				}
				int nextVersion = manifest.getIndexbyBitrate(path[i].bitrate);
				
				if (maxChangeQ < Math.abs(nextVersion - curVersion)) {
					maxChangeQ = Math.abs(nextVersion - curVersion);
				}
			}
		}
		
		double deltaBuffer = (double) (buffer.getOptimumThreshold() - buffer.getBufferSize())/1000 - totalUsedBuffer;
		
		//double totalCost = beta * Math.exp(-alpha * averageN) + gamma * maxChangeQ + sigma * Math.exp(lamda*deltaBuffer);
		double totalCost = (double)10*1/averageN + gamma * maxChangeQ + 0.08 * Math.exp(1*deltaBuffer);
		return totalCost;

	}
	void getBestPath(int curBitrate,int tarBitrate){
		ArrayList<Selection> path = new ArrayList<>();
		//trellis = buildTrellis(setPoint, 4);
		bestPath = trellis.get(0);
		
		double minCost = getOverallCost(bestPath.toArray(new Selection[bestPath.size()]),curBitrate,tarBitrate);
		for (int i = 1; i < trellis.size(); i++) {
			path = trellis.get(i);
			if(checkOrder(path,curBitrate)){
				double cost = getOverallCost(path.toArray(new Selection[path.size()]),curBitrate,tarBitrate);
				if (cost < minCost) {
					bestPath = path;
					minCost = cost;					
				}
			}
		}
		//System.out.println(minCost);
	}
	boolean checkOrder(ArrayList<Selection> path, int curBitrate){
		ArrayList<Selection> tempPath = (ArrayList<Selection>) path.clone();
		tempPath.add(0, new Selection(curBitrate, 1));
		/*System.out.println("check currentBR" + curBitrate);
		for(int i = 0; i<path.size();i++){
			System.out.println(path.get(i).bitrate);
		}*/
		int isIncrease = 0;
		//detect order
		for(int i = 1; i < tempPath.size();i++){			
			if(tempPath.get(i-1).bitrate-tempPath.get(i).bitrate<0)	{				
				isIncrease = 1;
				break;
			}else if(tempPath.get(i-1).bitrate-tempPath.get(i).bitrate > 0){
				isIncrease = -1;
				break;
			}			
		}
		//check order
		if(isIncrease == 0)
			return true;
		for(int i = 1; i < tempPath.size();i++){
			if((tempPath.get(i-1).bitrate-tempPath.get(i).bitrate<0)&&(isIncrease == -1))	{				
				return false;
			}else if((tempPath.get(i-1).bitrate-tempPath.get(i).bitrate > 0)&&(isIncrease==1)){
				return false;
			}
			
		}
		return true;
	}
	ArrayList<Double> callBuffFlowPath(ArrayList<Selection> bestPath){
		ArrayList<Double> buffFlowPath = new ArrayList<Double>();
		int bitrate,N;
		double curBuf = buffer.getBufferSize();
		buffFlowPath.add(curBuf);
		for(int i = 0; i < bestPath.size(); i++) {
			bitrate = bestPath.get(i).bitrate;
			N = bestPath.get(i).numOfSegment;
			curBuf += (double)(manifest.REAL_SEGMENT_DURATION *N* (1 - bitrate / overallThroughput));
			buffFlowPath.add(curBuf);
		}
		return buffFlowPath;	
	}
}
