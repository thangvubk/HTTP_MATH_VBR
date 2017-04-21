package logic;

import Client.Buffer;
import Client.Manifest;

public class Minh2AdaptiveAlgorithm extends AdaptationLogic {
    public Minh2AdaptiveAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        int nextNumberOfSegment;
        long bufferSize = buffer.getBufferSize();
        if (bufferSize >= 17100) {
            nextNumberOfSegment = 4;
        } else if (bufferSize >= 16400) {
            nextNumberOfSegment = 3;
        } else if (bufferSize >= 15800) {
            nextNumberOfSegment = 2;
        } else {
            nextNumberOfSegment = 1;
        }
        return new AdaptationDecision(getBitrateBasedOnLastSegmentThroughput(nextNumberOfSegment), nextNumberOfSegment);
    }
}
