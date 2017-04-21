package httpfake;

import Client.Buffer;
import Client.Manifest;
import Generator.GenerateScript;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by phanhaiphong on 7/28/15.
 */
public class HttpFake {
    private long currentTimeInMilis;
    private long rtt;
    private long delay;
    private int contentLength;
    private ArrayList<Long> segmentTimes = new ArrayList<>();
    
    public ArrayList<SegmentThroughput> segmentThrpList = new ArrayList<>();
    private Buffer buffer;
    private ArrayList<Double> bandwidth = new ArrayList<>();
    private ArrayList<Long> timeStamp = new ArrayList<>();
    private ArrayList<Double> rtts;
    private int downloadCount;
    private static final String INPUT_FILE = GenerateScript.TEXT_FILE_PATH;
    private Random random = new Random();

    public HttpFake(Buffer buffer) {
        this.buffer = buffer;
        //read input from bandwidth trace file
        try {
            try (BufferedReader inStream = new BufferedReader(new FileReader(INPUT_FILE))) {
                String currentLine;
                while ((currentLine = inStream.readLine()) != null) {
                    timeStamp.add((long) (1000.0 * Double.parseDouble(currentLine.split("\t")[0])));
                    bandwidth.add(Double.parseDouble(currentLine.split("\t")[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadCount = 0;
    }

    public HttpFake(Buffer buffer, ArrayList<Double> rtts) {
        this(buffer);
        this.rtts = rtts;
    }

    public void download(int numberOfSegments, int bitrate) {
        contentLength = (int) (bitrate * 1000 / 8 * Manifest.REAL_SEGMENT_DURATION / 1000.0);

        delay = buffer.diffFromOptimum();
        delay = delay > 0 ? delay : 0;
        currentTimeInMilis += delay;

        if (rtts == null) {
            int RTT = GenerateScript.PROPAGATION_DELAY_IN_MILIS * 2;
            rtt = (long) (RTT + random.nextDouble() * RTT * 0.15);
        } else {
            rtt = (long) (1000.0 * rtts.get(downloadCount));
        }
        currentTimeInMilis += rtt;

        segmentTimes.clear();
        int segmentSizeInKb = (int) (bitrate * Manifest.REAL_SEGMENT_DURATION / 1000.0);
        for (int i = 0; i < numberOfSegments; i++) {
            long downloadSegmentTime = downloadSegment(segmentSizeInKb);
            currentTimeInMilis += downloadSegmentTime;
            segmentTimes.add(downloadSegmentTime);
            //System.out.println(currentTimeInMilis);
            
            //calculate segment throughput
            SegmentThroughput segThrp = new SegmentThroughput();
            segThrp.time = (double)currentTimeInMilis/1000;
            if(segmentThrpList.size()==0){
            	//segThrp.time = currentTimeInMilis;
            	segThrp.throughput =(double) segmentSizeInKb*1000/currentTimeInMilis;
            	segmentThrpList.add(segThrp);
            }else{
            	double  downloadTime = (double)currentTimeInMilis/1000 - segmentThrpList.get(segmentThrpList.size()-1).time;
            	//consider delay for new request
            	if(i == 0)
            		downloadTime -= (double)delay/1000;
            	segThrp.throughput = (double)(segmentSizeInKb/downloadTime);
            	segmentThrpList.add(segThrp);
            }
        }
        downloadCount++;
    }

    /**
     * Does not increase currentTime
     *
     * @return download segment time
     */
    private long downloadSegment(int segmentSizeInKb) {
        //assume that no duplicate timestamp
        int index = Collections.binarySearch(timeStamp, currentTimeInMilis);
        if (index < 0) {
            index = (index + 1) * (-1) - 1;
            if (index == -1) {
                index = 0;
            }
        }
        //now index is the index of the largest element that <= currentTimeInMilis
        int downloadedSizeInKb = 0;
        long currentTimeStep = currentTimeInMilis;
        while (index < timeStamp.size() - 1) {
            //if it's not the last index
            int downloadSizeInKbUntilNextTime = (int) ((timeStamp.get(index + 1) - currentTimeStep) / 1000.0 * bandwidth.get(index));
            int remaining = downloadSizeInKbUntilNextTime - (segmentSizeInKb - downloadedSizeInKb);
            if (remaining > 0) {
                //means it will finish downloading before the next time step
                return (long) ((segmentSizeInKb - downloadedSizeInKb) / bandwidth.get(index) * 1000.0) + currentTimeStep - currentTimeInMilis;
            } else if (remaining == 0) {
                //gan nhu ko bao h xay ra: finish downloading right at the next time step
                return timeStamp.get(index + 1) - currentTimeInMilis;
            } else {
                downloadedSizeInKb += downloadSizeInKbUntilNextTime;
                currentTimeStep = timeStamp.get(index + 1);
                index++;
            }
        }
        //if not finish downloading when reaching the last time step: assume that the bandwidth will remain the same forever
        return (long) ((segmentSizeInKb - downloadedSizeInKb) / bandwidth.get(index) * 1000.0) + currentTimeStep - currentTimeInMilis;
    }

    public long getCurrentTimeInMilis() {
        return currentTimeInMilis;
    }

    public long getRtt() {
        return rtt;
    }

    public long getDelay() {
        return delay;
    }

    public int getContentLength() {
        return contentLength;
    }

    public ArrayList<Long> getSegmentTimes() {
        return segmentTimes;
    }

    public long getDownloadTime() {
        long allSegmentTimes = 0;
        for (Long time : segmentTimes) {
            allSegmentTimes += time;
        }
        return rtt + allSegmentTimes;
    }
}

