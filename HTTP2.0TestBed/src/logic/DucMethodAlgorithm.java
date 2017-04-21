package logic;

import Client.Buffer;
import Client.Manifest;

/**
 * Created by phanhaiphong on 9/23/15.
 */
public class DucMethodAlgorithm extends AdaptationLogic {
    private double ducAlpha = 0.3;

    public DucMethodAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        double minCost = 100;
        int numberOfPush = 1;
        for (int i = 1; i <= 4; i++) {
            double requestCost = 1.0 / i;
            double buffCost = (i * Manifest.REAL_SEGMENT_DURATION * 1.0) / (buffer.getBufferSize() - 3 * 1000);
            double totalCost = ducAlpha * buffCost + (1 - ducAlpha) * requestCost;
            if (totalCost < minCost) {
                numberOfPush = i;
                minCost = totalCost;
            }
        }
        
        return new AdaptationDecision(getBitrateBasedOnLastSegmentThroughput(numberOfPush), numberOfPush);
    }
}
