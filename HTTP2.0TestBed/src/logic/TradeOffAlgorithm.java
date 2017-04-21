package logic;

import Client.Buffer;
import Client.Manifest;

import java.util.LinkedHashMap;

/**
 * Created by phanhaiphong on 8/2/15.
 */
public class TradeOffAlgorithm extends AdaptationLogic {
    private final double BETA = 0.3;
    private int[] niArray = new int[]{1, 2, 3, 4};
    private int[] bitrateArray;
    private static final boolean CONSIDER_DELAY = true;

    private double maxScore;
    private double bufferScore;
    private double bitrateScore;

    public TradeOffAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
        bitrateArray = manifest.getBitrateArray();
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        setEstimatedNextThroughput(ESTIMATE_THRP_DYNAMIC);
        int resultBitrate = -1;
        int resultNi = -1;
        bufferScore = -1;
        bitrateScore = -1;
        maxScore = -1;

        for (int bitrate : bitrateArray) {
            for (int ni : niArray) {
                double tempBitrateScore = getBitrateScore(bitrate);
                double tempBufferScore = getBufferScore(ni, bitrate);
                double tempScore = BETA * tempBitrateScore + (1 - BETA) * tempBufferScore;
                //CY: in case score is equal, choose larger bitrate and larger ni
                if (tempScore >= maxScore) {
                    maxScore = tempScore;
                    bufferScore = tempBufferScore;
                    bitrateScore = tempBitrateScore;
                    resultBitrate = bitrate;
                    resultNi = ni;
                }
            }
        }

//        resultBitrate = getBitrateBasedOnOverallEstimatedThroughput();
//        for (int j = 0; j < niArray.length; j++) {
//            double tempBufferScore = getBufferScore(niArray[j], resultBitrate);
//            if (tempBufferScore >= maxScore) {
//                maxScore = tempBufferScore;
//                bufferScore = tempBufferScore;
//                bitrateScore = 0;
//                resultNi = niArray[j];
//            }
//        }
        return new AdaptationDecision(resultBitrate, resultNi);
    }

    //TODO: sao phải từ 1-5
    private double getBitrateScore(int bitrate) {
//        return (double) (bitrate - bitrateArray[0]) / (bitrateArray[bitrateArray.length - 1] - bitrateArray[0]);
        return Math.max(Math.min(0.000833 * bitrate + 0.833333, 5), 1);
    }

    private double getBufferScore(int ni, int bitrate) {
        double currentBuffer = CONSIDER_DELAY ? (buffer.diffFromOptimum() > 0 ? buffer.getOptimumThreshold() : buffer.getBufferSize()) : buffer.getBufferSize();
//        double newBuffer = currentBuffer + ni * Manifest.REAL_SEGMENT_DURATION - (rtt + bitrate * ni * (Manifest.REAL_SEGMENT_DURATION /1000.0) / lastSegmentBandwidth * 1000.0);
        //TODO: bảo dũng: nếu muốn tìm bitrate thì mới estimate throughput, còn nếu cần estimate time download thì dùng cái trên đây, chứ nếu dùng throughput thì thành ra là lấy làm tròn 2 lần. Nhưng tại sao kết quả chả ra cái j???
        double newBuffer = currentBuffer + ni * Manifest.REAL_SEGMENT_DURATION - bitrate * ni * (Manifest.REAL_SEGMENT_DURATION / 1000.0) / estimatedNextThroughput * 1000.0;
        newBuffer /= 1000.0;
        // System.out.println("hehe est buffer: "+ newBuffer);
        double score = 0.2 * Math.exp(newBuffer * 0.20118);
        //TODO: xem lại
        if (score < 1) {
            return 0;
        } else {
            return Math.max(Math.min(score, 5), 1);
        }
    }

    @Override
    public void writeExcelInfo(LinkedHashMap<String, Double> info) {
        super.writeExcelInfo(info);
        info.put("bitrate_score", bitrateScore);
        info.put("bf_score", bufferScore);
        info.put("total_score", maxScore);
    }
}
