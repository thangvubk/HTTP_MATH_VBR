package Client;

public class Buffer {
    //CY: Ä‘á»ƒ sÃ¡ng táº¡o thÃ¬ nÃªn nhá»› ráº±ng, má»�i thá»© Ä‘á»�u cÃ³ thá»ƒ thay Ä‘á»•i Ä‘Æ°á»£c.
    //Buflen must always >=0
    private long bufLen = 0;

    private long bufRunThreshold = 100;
    public long bufOptThreshold = 10 * 1000;

    public Buffer(boolean initialLength) {
        if (initialLength) {
            bufLen = bufOptThreshold; 
        }
    }

    public long getBufferSize() {
        return bufLen;
    }

    public void increaseBufferSize(long n) {
        Client.log("Increased buffer size from " + bufLen + "ms to " + (bufLen + n) + "ms");
        bufLen += n;
    }

    public void decreaseBufferSize(long n) {
        bufLen = bufLen >= n ? (bufLen - n) : 0;
    }

    /**
     * @return Buff - optimum
     */
    public long diffFromOptimum() {
        return bufLen - bufOptThreshold;
    }

    public long getOptimumThreshold() {
        return bufOptThreshold;
    }

    public boolean isBufferNotReadyToPlay() {
        return (bufLen < bufRunThreshold);
    }

    public boolean isBufferEmpty() {
        return (bufLen <= 0);
    }

    public void setBufOptThreshold(long buf) {
        bufOptThreshold = buf;
    }
}

