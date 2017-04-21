package logic;

import Client.Buffer;
import Client.Manifest;

import java.util.LinkedHashMap;

/**
 * Created by phanhaiphong on 9/24/15.
 */
public class Minh3AdaptiveAlgorithm extends AdaptationLogic {
    private static final long DANGER_THRESHOLD = 2000;
    private static final int NORMAL_MODE = 0;
    private static final int RECOVERY_MODE = 1;
    private int mode;
    private long lastBufferSize;

    private double recoveryAlpha;

    public Minh3AdaptiveAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
        mode = NORMAL_MODE;
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        int nextNumberOfSegment;
        //after the first request, in the 2nd decision, we first run this method and get the first result bufferSize. In the next request (3), we get meaning full lastBuffersize and get the second result buffersize
        long bufferSize = buffer.getBufferSize();
        long deltaBuffer = lastBufferSize - bufferSize;
        lastBufferSize = bufferSize; //no longer need lastBufferSize until end of loop
        if (numberOfDecidedRequest > 2 && deltaBuffer > DANGER_THRESHOLD) {
            mode = RECOVERY_MODE;
            //only then is deltaBuffer meaningful. We only use deltaBuffer once the numberofDecidedRequest >2
            recoveryAlpha = 1 - deltaBuffer / 4000.0;
            nextNumberOfSegment = 4;
            return new AdaptationDecision(getBitrateBasedOnLastSegmentThroughput(nextNumberOfSegment, recoveryAlpha), nextNumberOfSegment);
        } else {
            mode = NORMAL_MODE;
            if (bufferSize >= 17000) {
                nextNumberOfSegment = 4;
            } else if (bufferSize >= 14500) {
                nextNumberOfSegment = 3;
            } else {
                nextNumberOfSegment = 2;
            }
            return new AdaptationDecision(getBitrateBasedOnLastSegmentThroughput(nextNumberOfSegment), nextNumberOfSegment);
        }
    }

    public void writeExcelInfo(LinkedHashMap<String, Double> info) {
        super.writeExcelInfo(info);
        info.put("mode", (double) mode);
        switch (mode) {
            case NORMAL_MODE:
                info.put("alpha", alpha);
                break;
            case RECOVERY_MODE:
                info.put("alpha", recoveryAlpha);
                break;
        }
    }
}
