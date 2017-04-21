package logic;

import Client.Buffer;
import Client.Manifest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class NewAdaptiveAlgorithm extends AdaptationLogic {
    private int constantNumberOfSegments = 1;
    private static final int HISTORY_SIZE = 10;
    private LinkedList<Double> history = new LinkedList<>();
    private LinkedList<Double> historyDiff = new LinkedList<>();

    public NewAdaptiveAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
    }

    private boolean canUseAlgorithm() {
        return history.size() == HISTORY_SIZE;
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        int newNi;
        if (!canUseAlgorithm()) {
            //in the first HISTORY_ SIZE segments: just push constant number of segments
            newNi = constantNumberOfSegments;
        } else {
            System.out.println("haha: mean: " + getMean(history) + ", variance: " + getVariance(history) + ", std: " + getStdDev(history)+ ", meandiff: "+getMean(historyDiff)+", stddiff: "+getStdDev(historyDiff));
            double stddev = getStdDev(history);
            if (stddev > 500) {
//                //if it's increasing
//                if (getMean(historyDiff) > 10) {
//                    return 4;
//                } else {
//                    return 1;
//                }
                newNi = 1;
//            } else {
//                return 3;
//            }
            } else if (stddev > 400) {
                newNi = 2;
            } else if (stddev > 300) {
                newNi = 3;
            } else {
                newNi = 4;
            }
        }
        return new AdaptationDecision(getBitrateBasedOnEstimatedThroughput(ESTIMATE_THRP_AGGRESSIVE), newNi);
    }

    @Override
    public void updateDownloadInfo(long downloadTime, ArrayList<Long> segmentDownloadTimes, long rtt, int segmentSizeInByte, int numberOfSegments) {
        super.updateDownloadInfo(downloadTime, segmentDownloadTimes, rtt, segmentSizeInByte, numberOfSegments);
        for (long segmentTime : segmentDownloadTimes) {
            double throughput = (segmentSizeInByte * 8) / segmentTime; //this is real throughput, not include rtt
            if (historyDiff.size() == HISTORY_SIZE) {
                historyDiff.remove(0);
            }
            if (!history.isEmpty()) {
                historyDiff.add(throughput - history.getLast());
            }
            if (history.size() == HISTORY_SIZE) {
                history.remove(0);
            }
            history.add(throughput);
        }
        System.out.print("hihi all segment throughput: ");
        for (Double thrp : history) {
            System.out.print(thrp + ", ");
        }
        System.out.println();
        System.out.print("hihi all diff:               ");
        for (Double thrp : historyDiff) {
            System.out.print(thrp + ", ");
        }
        System.out.println();
    }

    @Override
    public void writeExcelInfo(LinkedHashMap<String, Double> info) {
        super.writeExcelInfo(info);
        if (canUseAlgorithm()) {
            info.put("mean", getMean(history));
            info.put("std", getStdDev(history));
            info.put("mean diff", getMean(historyDiff));
            info.put("std diff", getStdDev(historyDiff));
        }
    }

    private double getMean(List<Double> history) {
        double sum = 0;
        for (double a : history)
            sum += a;
        return sum / history.size();
    }

    private double getVariance(List<Double> history) {
        double mean = getMean(history);
        double temp = 0;
        for (double a : history) {
            temp += (mean - a) * (mean - a);
		}
        return temp / history.size();
    }

    private double getStdDev(List<Double> history) {
        return Math.sqrt(getVariance(history));
    }

}
