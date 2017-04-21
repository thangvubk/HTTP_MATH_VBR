package Generator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Presentation {
    private static final String OUTPUT_DIR = GenerateScript.OUTPUT_DIR + File.separator + "video";
    private static final int LOWEST_BITRATE = 100;
    private static final int HIGHEST_BITRATE = 5000;
    private static final int STEP_BITRATE = 100;

    //toggle between code kiểu cũ (false) (http/1.1 - anh Hưng) và code kiểu mới (Http/2) (true) của Phong
    private static final boolean HTTP2 = true;
    private static final int MIN_REAL_SEGMENT_DURATION = 100;
    private static final int MAX_REAL_SEGMENT_DURATION = 2000;
    private static final int STEP_REAL_SEGMENT_DURATION = 100;

    //Khi HTTP2 == true: max segment duration that we will call at once: Thay vì làm như thật, thì giờ mình làm mỗi bitrate có 20 cái segment, đánh số thứ tự từ 1 đến 20, nếu 1 lần gọi 3 thằng thì cho 3 thằng 1 2 3 ra, gọi 20 thằng thì cho 20 thằng ra. Chứ nếu làm thật, thì bandwidth trace là 1600s, nếu segment duration là nửa giây thì mỗi bitrate phải làm 3200 cái, cực nhiều, không cần thiết.
    private static final int MAX_SEGMENT_DURATION = 20000;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (HTTP2) {
            for (int realSegmentDuration = MIN_REAL_SEGMENT_DURATION; realSegmentDuration <= MAX_REAL_SEGMENT_DURATION; realSegmentDuration += STEP_REAL_SEGMENT_DURATION) {
                for (int bitRate = LOWEST_BITRATE; bitRate <= HIGHEST_BITRATE; bitRate += STEP_BITRATE) {
                    String subPath = OUTPUT_DIR + File.separator + realSegmentDuration + File.separator + bitRate;
                    new File(subPath).mkdirs();
                    int size = (int) (bitRate * 1000 / 8 * realSegmentDuration / 1000f);
                    for (int i = 1; i <= MAX_SEGMENT_DURATION / realSegmentDuration; i++) {
                        RandomAccessFile file = new RandomAccessFile(subPath + File.separator + "seg-" + i, "rw");
                        file.setLength(size);
                        file.close();
                    }
                }
            }
        } else {
            for (int segmentDurationInSeconds = 1; segmentDurationInSeconds <= MAX_SEGMENT_DURATION / 1000; segmentDurationInSeconds++) {
                String subPath = OUTPUT_DIR + File.separator + segmentDurationInSeconds + "s";
                new File(subPath).mkdirs();
                for (int bitRate = LOWEST_BITRATE; bitRate <= HIGHEST_BITRATE; bitRate += STEP_BITRATE) {
                    //bitrate unit: kB/s
                    //1000 là milliseconds: bitrate*1000/8 : from bit/ms to byte per second. data size = byte/second * second = byte
                    int dataSize = bitRate * 1000 / 8 * segmentDurationInSeconds;
                    //create segment files
                    new RandomAccessFile(subPath + File.separator + bitRate, "rw").setLength(dataSize);
                }
            }
        }
    }

}
