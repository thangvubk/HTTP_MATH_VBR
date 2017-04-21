package Client;

/**
 * Manage server information: bitrate arrays, video duration, segment duration, url.
 */
public class Manifest { //TODO: vá»� static háº¿t cho tÃ´i
    //TODO: trÃªn thá»±c táº¿ video chá»‰ cÃ³ 1 vÃ i bitrate thÃ´i, ko nhiá»�u nhÆ° tháº¿ nÃ y
    public static final String SERVER_ADDRESS = "myserver";
    private static final int START_BITRATE = 200;
    private static final int STEP_SIZE = 200;
    public static final int NUMBER_OF_BITRATES = 14;

    public static final long REAL_SEGMENT_DURATION = 2000;

    public static final long VIDEO_DURATION = 600 * 1000;

    //bitrate array is a subset of all the bitrates (from 100 to 5000) we have in the server
    public static int[] bitrateArray = new int[NUMBER_OF_BITRATES];

    //private to make this class singleton
    public Manifest() {
//        for (int i = 0; i < NUMBER_OF_BITRATES; i++) {
//            bitrateArray[i] = i * STEP_SIZE + START_BITRATE;
//        }
		bitrateArray = new int[]{100, 150, 200, 250, 300, 400, 500, 700, 900, 1200, 1500, 2000, 2500,3000};
    }

    public int[] getBitrateArray() {
        return bitrateArray;
    }

    public boolean isMaxBitrate(int bitrate) {
        return (bitrate == bitrateArray[NUMBER_OF_BITRATES - 1]);
    }

    public boolean isMinBitare(int bitrate) {
        return (bitrate == bitrateArray[0]);
    }

    //fragsize = bitrate in byte * segment duration
    public int getRealSegmentSizeInBytes(int bitrate) {
        return (int) (bitrate * REAL_SEGMENT_DURATION / 8);
    }

    public int getIndexbyBitrate(int bitrate) {
        for (int i = 0; i < NUMBER_OF_BITRATES; i++) {
            if (bitrateArray[i] == bitrate) {
                return i;
            }
        }
        throw new RuntimeException("Cannot find index of this bitrate: " + bitrate);
    }

    /**
     * Return max bitrate if input is already max
     */
    public int nextBitrate(int bitrate) {
        return bitrateArray[Math.min(getIndexbyBitrate(bitrate) + 1, NUMBER_OF_BITRATES - 1)];
    }

    /**
     * Return the min bitrate if the bitrate input is already min
     */
    public int previousBitrate(int bitrate) {
        return bitrateArray[Math.max(0, getIndexbyBitrate(bitrate) - 1)];
    }

    /**
     * Select the maximum bitrate that is <= this throughput. If no bitrate <= throughput, return the min bitrate
     */
    public int selectFloorBitrate(double throughput) {
        int i = NUMBER_OF_BITRATES - 1;
        while (i > 0 && bitrateArray[i] > throughput) i--;
        return bitrateArray[i];
    }

    //Choose the bitrate that is nearest the throughput (< or > or =)
    public int selectBitrateNearestThroughput(double throughput) {
        int pre = selectFloorBitrate(throughput);
        int next = nextBitrate(pre);
        //works in the case of throughput < all bitrates
        if (next - throughput < throughput - pre)
            return next;
        else
            return pre;
    }

    /**
     * Select the min bitrate that is >= this throughput
     */
    public int selectCeilBitrate(double throughput) {
//        int pre = selectFloorBitrate(Thrp);
//        return nextBitrate(pre);
        //code cÅ© sai trong trÆ°á»�ng há»£p throughput < táº¥t cáº£ bitrate. Khi Ä‘Ã³ nÃ³ sáº½ chá»�n 0, vÃ  next tá»©c lÃ  1. Trong khi mÃ¬nh muá»‘n cÃ¡i 0.
        int i = 0;
        while (i < NUMBER_OF_BITRATES - 1 && bitrateArray[i] < throughput) i++;
        return bitrateArray[i];
    }
}

