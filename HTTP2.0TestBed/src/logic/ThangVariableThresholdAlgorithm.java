package logic;

import Client.Buffer;
import Client.Manifest;

public class ThangVariableThresholdAlgorithm extends AdaptationLogic {
	private int constantNumberOfSegments = 1;
	private long bHigh = buffer.bufOptThreshold;
	private double lowBuffer = bHigh / 2;
	private int bMin = 10000;
	private double cost, costR, costBR, costBuf;
	int nextNSegment = 5;

	public ThangVariableThresholdAlgorithm(Buffer buffer, Manifest manifest) {
		super(buffer, manifest);
		buffer.setBufOptThreshold(bHigh);
	}

	@Override
	protected AdaptationDecision decideNextRequest() {
				int nextBitrate = 0;
		nextNSegment = 1;
		int SD = (int) Manifest.REAL_SEGMENT_DURATION;
		double bCur = buffer.getBufferSize();
		int curBitrate = currentParams.bitrate;
		setEstimatedNextThroughput(ESTIMATE_THRP_AGGRESSIVE);
		double thrpLow = estimatedNextThroughput * 0.5;

		int kCur = manifest.getIndexbyBitrate(curBitrate);
		double bLow = bMin;

		if (estimatedNextThroughput < curBitrate) {
			// compute blow
			int kTarget = manifest.getIndexbyBitrate(manifest.selectFloorBitrate(thrpLow));
			for (int k = kTarget; k < kCur; k++) {
				bLow -= SD * (1 - (double) Manifest.bitrateArray[k] / thrpLow);
			}
			System.out
					.println(bLow + "\t"+ bCur + "\t"+ kTarget + "\t" + kCur + "\t" + thrpLow + "\t" + curBitrate);

			if (bCur <= bMin)
				nextBitrate = manifest.bitrateArray[0];
			else if (bCur <= bLow) {
				System.out.println("bCur < bLow check");
				int selectedDeltaK = 0;
				System.out.println("deltaK" + selectedDeltaK);
				for (int deltaK = 1; deltaK < (kCur - kTarget); deltaK++) {
					double deltaB = 0;
					int k = kCur-deltaK;
					while (k >= kTarget) {
						deltaB += SD * (1 - Manifest.bitrateArray[k] / thrpLow);

						// exception
						if (k > kTarget && k - deltaK < kTarget) {
							k = kTarget;
							continue;
						}
						k -= deltaK;

					}
					System.out.println(deltaB);
					if (bCur + deltaB > bMin) {
						selectedDeltaK = deltaK;
						
						break;
					} else {
						System.err.println("deltaK");
					}

				}
				System.out.println("deltaK" + selectedDeltaK);
				nextBitrate = Manifest.bitrateArray[kCur - selectedDeltaK];
			} else {
				if (bCur > bLow - SD * (1 - Manifest.bitrateArray[kCur] / thrpLow)) {
					
					//find the next N segment 
					nextBitrate = curBitrate;
					int maxN = (int) (calculateNextN2(nextBitrate,lowBuffer)*((double)3000/nextBitrate));
					int i = 0;
					for(i = 1 ; i < maxN;i++){
						bCur += SD * (1 - curBitrate / thrpLow);
						if (bCur < bLow)
							break;
					}
					nextNSegment = i;
					System.out.println("check Nseg " +nextNSegment);
					
					
				} else {
					nextBitrate = manifest.previousBitrate(curBitrate);
				}

			}
		} else {
			// Hung's increase case
			if (bCur < bHigh) {
				nextBitrate = curBitrate;
			} else {
				nextBitrate = manifest.selectFloorBitrate(estimatedNextThroughput);
				//nextBitrate = manifest.nextBitrate(curBitrate);
			}
			/*if(manifest.nextBitrate(curBitrate) < estimatedNextThroughput){
				nextBitrate = manifest.nextBitrate(curBitrate);
			}else{
				nextBitrate = curBitrate;
			}*/
			nextNSegment = (int) (calculateNextN2(nextBitrate, lowBuffer)*((double)Manifest.bitrateArray[Manifest.NUMBER_OF_BITRATES-1]/nextBitrate));

		}
		
		if(nextNSegment > 10)
			nextNSegment = 10;
		
		return new AdaptationDecision(nextBitrate, nextNSegment);
		//return new AdaptationDecision(nextBitrate, 10);

	}

	@Override
	protected AdaptationDecision decideFirstRequest() {

		return new AdaptationDecision(manifest.bitrateArray[manifest.NUMBER_OF_BITRATES-1], constantNumberOfSegments);
	}
	//int findMaxNForDecreaseCase()

	int calculateNextN(int BRNext) {
		double alpha, beta, gamma;
		int nRes = 7;
		int minIndex = 0;
		int minIndexBR = 0;
		int BRMax = (int) manifest.bitrateArray[Manifest.NUMBER_OF_BITRATES - 1];
		int BRMin = (int) manifest.bitrateArray[0];
		double curBuf = (double) buffer.getBufferSize() / manifest.REAL_SEGMENT_DURATION;
		double bLow = (double) lowBuffer / manifest.REAL_SEGMENT_DURATION;
		// ////////////////////////////
		// calculate cost//////////////
		// ////////////////////////////

		if (buffer.getBufferSize() > lowBuffer) {
			double minCost = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.5 * iRes);
				costBR = (double) iRes / (BRMax + BRMin - BRNext);
				costBuf = (double) iRes / (curBuf - bLow);
				alpha = 1.5;
				beta = 0;
				gamma = 1;
				cost = costR * alpha + costBR * beta + costBuf * gamma;
				// System.out.println(costR + "\t" + costBR + "\t" + cost);
				if (minCost > cost) {
					minCost = cost;
					minIndex = iRes;
				}
			}

			double minCostBR = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.1 * iRes);
				costBR = (double) iRes / (BRMax + 1 - BRNext);
				costBuf = (double) iRes / (curBuf - bLow);
				alpha = 0.15;
				beta = 20;
				cost = costR * alpha + costBR * beta;
				// System.out.println((costR*alpha) + "\t" + (costBR*beta) +
				// "\t" + cost);
				if (minCostBR > cost) {
					minCostBR = cost;
					minIndexBR = iRes;
				}
			}
			if (minIndex < minIndexBR)
				minIndex = minIndexBR;
		} else {
			double minCost = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.1 * iRes);
				costBR = (double) iRes / (BRMax + 1 - BRNext);
				costBuf = (double) iRes / (curBuf - bLow);
				alpha = 0.15;
				beta = 20;
				cost = costR * alpha + costBR * beta;
				// System.out.println((costR*alpha) + "\t" + (costBR*beta) +
				// "\t" + cost);
				if (minCost > cost) {
					minCost = cost;
					minIndex = iRes;
				}
			}
		}
		//System.out.println(BRMax + " " + BRMin + " " + BRNext);
		return minIndex;
	}

	int calculateNextN2(int BRNext, double lowBuffer) {
		double alpha, beta, gamma;
		int nRes = 7;
		int minIndex = 0;
		int minIndexBR = 0;
		int BRMax = (int) manifest.bitrateArray[Manifest.NUMBER_OF_BITRATES - 1];
		int BRMin = (int) manifest.bitrateArray[0];
		double curBuf = (double) buffer.getBufferSize() / manifest.REAL_SEGMENT_DURATION;
		double bLow = (double) lowBuffer / manifest.REAL_SEGMENT_DURATION;
		// ////////////////////////////
		// calculate cost//////////////
		// ////////////////////////////

		if (buffer.getBufferSize() > lowBuffer) {
			double minCost = Double.MAX_VALUE;
			for (int iRes = 1; iRes <= nRes; iRes++) {
				costR = (double) Math.exp(-0.2 * iRes);
				costBR = (double) iRes / (BRMax + BRMin - BRNext);
				costBuf = (double) iRes / (curBuf);
				alpha = 0.5;
				beta = 0;
				gamma = (1-alpha);
				cost = costR * alpha + costBR * beta + costBuf * gamma;
				// System.out.println(costR + "\t" + costBR + "\t" + cost);
				if (minCost > cost) {
					minCost = cost;
					minIndex = iRes;
				}
			}
			return minIndex;
		}
		return 1;
	}
	
}
