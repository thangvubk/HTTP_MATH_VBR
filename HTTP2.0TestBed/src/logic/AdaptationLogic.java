package logic;

import Client.Buffer;
import Client.Manifest;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import logic.AdaptationDecision;

/**
 * Only works inside Downloader thread
 */
public abstract class AdaptationLogic {
    protected final double alpha = 0.9;
    protected Manifest manifest;
    protected Buffer buffer;
    protected int numberOfDecidedRequest = 0;
	//protected int currentBitrate = 0;
	protected AdaptationDecision currentParams;

    //Just temp variables from updateDownloadInfo() to decide()
    private long downloadTime;
    protected int numberOfSegments;
    private ArrayList<Long> segmentDownloadTimes;
    protected long rtt;
    private int segmentSizeInByte;
    protected double overallThroughput;
    protected double lastSegmentBandwidth;
	protected ArrayList<Double> throughputArray;
	protected ArrayList<Integer> requestArray;

    protected static final int ESTIMATE_THRP_AGGRESSIVE = 0;
    protected static final int ESTIMATE_THRP_DYNAMIC = 1;
	protected static final int ESTIMATE_SMOOTH_THROUGHPUT = 2;
	protected static final int ESTIMATE_SMOOTH_2_SEGMENT_THROUGHPUT = 3;
	protected static final int NUMBER_OF_SMOOTH_SEGMENT = 10;
	protected static final int NUMBER_OF_SMOOTH_SEGMENT_SWITCH_DOWN = 5;
    protected double estimatedNextThroughput = 0;
	protected double estimatedThrpForSwitchDownBufferBase = -1;
	private int index = 0;

    public AdaptationLogic(Buffer buffer, Manifest manifest) {
        this.buffer = buffer;
        this.manifest = manifest;
		throughputArray = new ArrayList<Double>();
		requestArray = new ArrayList<Integer>();
		currentParams = new AdaptationDecision(0,0);
        
    }

    public AdaptationDecision decide() {
		//this.currentParams = new AdaptationDecision(0,0);
		AdaptationDecision decision;
        if (numberOfDecidedRequest++ == 0) {			
			//this.currentParams = decideFirstRequest;
			decision = decideFirstRequest();
			this.currentParams = decision;
			//this.currentBitrate = decision.bitrate;
            return decision;
        } else {
			//this.currentParams = decideNextRequest;
            decision = decideNextRequest();
			this.currentParams = decision;
			//this.currentBitrate = decision.bitrate;
            return decision;
        }
		//return currentParams;
    }

    protected int getBitrateBasedOnEstimatedThroughput(int throughputEstimationMode) {
        setEstimatedNextThroughput(throughputEstimationMode);
		
        return manifest.selectFloorBitrate(estimatedNextThroughput * alpha);
    }

    protected void setEstimatedNextThroughput(int throughputEstimationMode) {
        switch (throughputEstimationMode) {
            case ESTIMATE_THRP_AGGRESSIVE:
            default:
                this.estimatedNextThroughput = overallThroughput;
                break;
            case ESTIMATE_THRP_DYNAMIC:
                if (this.estimatedNextThroughput == -1) {
                    this.estimatedNextThroughput = overallThroughput;
                } else {
                    double k = 21;
                    double p0 = 0.1;
                    // for now estimatedNextThroughput is still the old value
                    double deviation = Math.abs(overallThroughput - estimatedNextThroughput) / estimatedNextThroughput;
                    double delta = 1 / (1 + Math.exp(-k * (deviation - p0)));
                    // update estimated throughput
                    this.estimatedNextThroughput = (1 - delta) * estimatedNextThroughput + delta * overallThroughput;
                }
                break;
			case ESTIMATE_SMOOTH_THROUGHPUT:
				double sigma = 0.125;
				if (index == 0){
					estimatedNextThroughput = overallThroughput;
				}else{
					estimatedNextThroughput = overallThroughput*sigma + estimatedNextThroughput*(1-sigma);
				}
				break;
				
			case ESTIMATE_SMOOTH_2_SEGMENT_THROUGHPUT:
//				this.throughputArray.add((double)overallThroughput);
//				int size = this.throughputArray.size();
//				double sigma = 0.5;
//				if(size < 2)
//					this.estimatedNextThroughput = throughputArray.get(0);
//					
//				else
//					estimatedNextThroughput = throughputArray.get(size-1)*sigma + estimatedNextThroughput*(1-sigma);
//				break;
			//case ESTIMATE_SMOOTH_THROUGHPUT:	
				
        }
    }

    /**
     * This change the value of estimatedNextThroughput
     */
    protected int getBitrateBasedOnLastSegmentThroughput(int nextNumberOfPush) {
        this.estimatedNextThroughput = (segmentSizeInByte * 8) / (rtt / (double) nextNumberOfPush + segmentDownloadTimes.get(segmentDownloadTimes.size() - 1));
        return manifest.selectFloorBitrate(estimatedNextThroughput * alpha);
    }

    /**
     * This change the value of estimatedNextThroughput
     */
    protected int getBitrateBasedOnLastSegmentThroughput(int nextNumberOfPush, double alpha) {
        this.estimatedNextThroughput = (segmentSizeInByte * 8) / (rtt / (double) nextNumberOfPush + segmentDownloadTimes.get(segmentDownloadTimes.size() - 1));
        return manifest.selectFloorBitrate(estimatedNextThroughput * alpha);
    }

    protected abstract AdaptationDecision decideNextRequest();

    protected AdaptationDecision decideFirstRequest() {
        return new AdaptationDecision(1000, 1);
    }

    /**
     * Information is updated after each segment download
     */
    public void updateDownloadInfo(long downloadTime, ArrayList<Long> segmentDownloadTimes, long rtt, int segmentSizeInByte, int numberOfSegments) {
        this.downloadTime = downloadTime;
        this.segmentDownloadTimes = segmentDownloadTimes;
        this.rtt = rtt;
        this.segmentSizeInByte = segmentSizeInByte;
        this.numberOfSegments = numberOfSegments;
        this.lastSegmentBandwidth = (double) (segmentSizeInByte * 8) / segmentDownloadTimes.get(segmentDownloadTimes.size() - 1);
        this.overallThroughput = (double) (numberOfSegments * segmentSizeInByte * 8) / (rtt + downloadTime);
    }

    /**
     * Called before excel is updated, before updateDownloadInfo, after download has finished and all other values has been entered
     */
    public void writeExcelInfo(LinkedHashMap<String, Double> info) {
        if (numberOfDecidedRequest > 1) {
            info.put("estimated-thrp", this.estimatedNextThroughput);
        }
    }
}

