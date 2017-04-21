package logic;

import Client.Buffer;
import Client.Manifest;
public class ThangCostFunctionAlgorithm extends AdaptationLogic {
    private int constantNumberOfSegments = 1;
	
	private int lowBuffer = 25000;
	private int minBuffer = 10000;
	int BRMax = (int) manifest.bitrateArray[manifest.NUMBER_OF_BITRATES-1];
	int BRMin = (int) manifest.bitrateArray[0];
	double curBuf;
	private int maxBuffer = 50;
    public ThangCostFunctionAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
		buffer.setBufOptThreshold(50000);
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
		int curBitrate = currentParams.bitrate;
		//throughputArray.add(overallThroughput);
		setEstimatedNextThroughput(2); //instant throughput method
		/*if(buffer.getBufferSize() < minBuffer){
			nextBitrate = manifest.bitrateArray[0];
		} else if (buffer.getBufferSize() < lowBuffer){
			if(currentParams.bitrate > estimatedNextThroughput)
				nextBitrate = manifest.previousBitrate(currentParams.bitrate);
		}else{
			if(estimatedNextThroughput > manifest.nextBitrate(currentParams.bitrate))
				nextBitrate = manifest.nextBitrate(currentParams.bitrate);
		}*/
		//nextBitrate = 2000;
		int nextBitrate = computeNextBitrate(curBitrate);
		int nextNSegment = calculateNextN(nextBitrate);
		
		//System.out.println(throughputArray.size() + "\t" + throughputArray.get(throughputArray.size()-1));
		
		return new AdaptationDecision(nextBitrate, nextNSegment);
		//return new AdaptationDecision(nextBitrate, 4);
        
    }

    @Override
    protected AdaptationDecision decideFirstRequest() {
		
        return new AdaptationDecision(3000, constantNumberOfSegments);
    }
	int calculateNextN(int BRNext){
		double alpha, beta, gamma;
		double cost, costR, costBR, costBuf;
		int nRes = 7;
		int minIndex = 0;
		int minIndexBR = 0;
		curBuf =  (double) buffer.getBufferSize()/manifest.REAL_SEGMENT_DURATION;
		
		double bLow = (double) lowBuffer/manifest.REAL_SEGMENT_DURATION;
		//////////////////////////////
		//calculate cost//////////////
		//////////////////////////////
		
		if (buffer.getBufferSize() > lowBuffer) {
			double minCost = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.5*iRes);
				costBR = (double)iRes / (BRMax + BRMin - BRNext);
				costBuf = (double)iRes/( curBuf - bLow);
				alpha = 1.5;beta = 0;gamma = 1;
				cost = costR * alpha + costBR * beta+ costBuf*gamma  ;
				//System.out.println(costR + "\t" + costBR + "\t" + cost);
				if (minCost > cost) {
					minCost = cost;
					minIndex = iRes;
				}
			}
			
			double minCostBR = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.1*iRes);
				costBR = (double)iRes / (BRMax + 1 - BRNext);
				costBuf = (double)iRes/( curBuf - bLow);
				alpha = 0.15; beta = 20;
				cost = costR * alpha + costBR * beta;
				//System.out.println((costR*alpha) + "\t" + (costBR*beta) + "\t" + cost);
				if (minCostBR > cost) {
					minCostBR = cost;
					minIndexBR = iRes;
				}
			}
			if (minIndex < minIndexBR)
				minIndex = minIndexBR;
		}else{
			double minCost = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.1*iRes);
				costBR = (double)iRes / (BRMax + 1 - BRNext);
				costBuf = (double)iRes/( curBuf - bLow);
				alpha = 0.15; beta = 20;
				cost = costR * alpha + costBR * beta;
				//System.out.println((costR*alpha) + "\t" + (costBR*beta) + "\t" + cost);
				if (minCost > cost) {
					minCost = cost;
					minIndex = iRes;
				}
			}
		}
		//System.out.println(BRMax + " " + BRMin + " " + BRNext);
		return minIndex;
	}
	int computeNextBitrate(int curBR){
		double alpha = 30, beta = 0.25*1.2, gamma = 35 , sigma = 35;
		double costBuf, costS, rewardQ;
		double minCost = Double.MAX_VALUE;
		int indexMinBR = 0;
		curBuf =  (double) buffer.getBufferSize()/manifest.REAL_SEGMENT_DURATION;
		for(int iBR = 0;iBR<manifest.NUMBER_OF_BITRATES;iBR++){
			int nextBitrate = manifest.bitrateArray[iBR];
			int Nnext = calculateNextN(nextBitrate);
			double destBuf = curBuf + manifest.REAL_SEGMENT_DURATION*Nnext/1000*(1-(double)nextBitrate/estimatedNextThroughput);
			costBuf = Math.exp(beta/2*Math.abs(maxBuffer-destBuf));
			costS = Math.pow(manifest.getIndexbyBitrate(nextBitrate) -manifest.getIndexbyBitrate(curBR),2);
			rewardQ = iBR + 1;
			System.out.println(destBuf +"\t"+nextBitrate + "   " + curBR + "   " +manifest.getIndexbyBitrate(nextBitrate)+ "  " +manifest.getIndexbyBitrate(curBR)+"  "+ iBR + "   " + alpha*costBuf + "   " +gamma*costS+ "  " + sigma*rewardQ);
			if (minCost > alpha*costBuf + gamma*costS - sigma*rewardQ)
			{
				minCost = alpha*costBuf + gamma*costS - sigma*rewardQ;
				indexMinBR = iBR;
			}
		}
		
		System.out.println(alpha + "   " + indexMinBR);
		
		System.out.println("minCost" + minCost);
		return manifest.bitrateArray[indexMinBR];
		
	}
}
