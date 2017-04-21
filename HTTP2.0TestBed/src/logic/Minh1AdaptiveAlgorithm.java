package logic;

import Client.Buffer;
import Client.Manifest;

/**
 * Created by phanhaiphong on 6/22/15.
 */
public class Minh1AdaptiveAlgorithm extends AdaptationLogic {
    private static final long DANGER_THRESHOLD = 15000; //CY: this must < Manifest.Target buffer

    public Minh1AdaptiveAlgorithm(Buffer buffer, Manifest manifest) {
        super(buffer, manifest);
    }

    @Override
    protected AdaptationDecision decideNextRequest() {
        double muy;
        switch (numberOfSegments) {
            case 1:
            default:
//                muy = 1.151;
//                muy = 1.187;
                muy = 1.146;
                break;
            case 2:
//                muy = 1.253;
//                muy = 1.338;
                muy = 1.248;
                break;
            case 3:
//                muy = 1.295;
                muy = 1.389;
                break;
            case 4:
//                muy = 1.32;
                muy = 1.392;
                break;
        }
        //TODO: thực ra thằng minh nó làm là thứ nhất 4 cái đưòng ấy nó làm full (thật k), và nó tính theo overall throughput để ra được mấy cái đường ấy. thứ 2 là cái chọn bỉtate tiếp theo của nó cũng là overall throughput.
        int number = (int) ((DANGER_THRESHOLD - buffer.getBufferSize()) / (Manifest.REAL_SEGMENT_DURATION * (1 - muy * alpha)));
        System.out.println("chon ne: " + number);
        int nextNumberOfRequest = Math.max(1, Math.min(4, number));
        return new AdaptationDecision(getBitrateBasedOnEstimatedThroughput(ESTIMATE_THRP_AGGRESSIVE), nextNumberOfRequest);
    }
}
