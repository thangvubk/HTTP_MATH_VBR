package logic;

import Client.Buffer;
import Client.Manifest;

public class AggressiveAlgorithm extends AdaptationLogic {
    private int constantNumberOfSegments = 1;

    //Minh: Aggressive nghĩa là nó thay đổi rất là nhanh, chỉ phụ thuộc vào cái đằng trước
    public AggressiveAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        return new AdaptationDecision(getBitrateBasedOnLastSegmentThroughput(constantNumberOfSegments), constantNumberOfSegments);
    }

    @Override
    protected AdaptationDecision decideFirstRequest() {
        return new AdaptationDecision(3000, constantNumberOfSegments);
    }
}
