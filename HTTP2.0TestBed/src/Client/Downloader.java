package Client;

import httpfake.HttpFake;
import logic.AdaptationDecision;
import logic.AdaptationLogic;
import logic.SDPAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Downloader implements Runnable {
    private Buffer buffer;
    private AdaptationLogic adaptationLogic;

    private ExcelExporter excelExporter;
    private LinkedHashMap<String, Double> info = new LinkedHashMap<>();
    private static final String CHOSEN_BITRATE_STRING = "Chosen bitrate: ";
    private final double MAX_DURATION;
    private HttpFake httpFake;
    private boolean isPrint = true;
    
    public Downloader(ExcelExporter excelExporter, Buffer buffer, AdaptationLogic adaptationLogic, double maxDurationInMillis, boolean fakeHttp) {
        this.buffer = buffer;
        this.adaptationLogic = adaptationLogic;
        this.MAX_DURATION = maxDurationInMillis;
        this.excelExporter = excelExporter;
        excelExporter.initExportSheet();
        if (fakeHttp) {
            httpFake = new HttpFake(buffer);
        }
    }

    @Override
    public void run() {
        Client.log("Chosen segment duration: " + Manifest.REAL_SEGMENT_DURATION);
        //stop when downloaded the whole video or when finished dummynet script
        long startTime = httpFake == null ? System.currentTimeMillis() : 0;
        long lastFinishTime = startTime;
        for (long timeDownloaded = 0; timeDownloaded < Manifest.VIDEO_DURATION && lastFinishTime - startTime < MAX_DURATION; ) {
            // First, use the adaptation logic to get the url
            System.out.println("\n\n");
            System.out.println("************************** START DOWNLOAD SEGMENT: time downloaded: " + timeDownloaded / 1000.0 + ", time elapsed: " + (lastFinishTime - startTime) / 1000.0);
            AdaptationDecision decision = adaptationLogic.decide();

            //if buffer is larger than optimum, make it reduced to optimum
            //we only delay when we have allowed Player to play
            long delayBeforeDown;
            long rtt;
            long downloadTime;
            int size;
            ArrayList<Long> segmentDownloadTimes;

            if (httpFake == null) {
                delayBeforeDown = buffer.diffFromOptimum();
                if (delayBeforeDown > 0) {
                    try {
                        Thread.sleep(delayBeforeDown);
                        //CY: after sleeping, you must immediately run the download
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    delayBeforeDown = 0;
                }

                //start downloading
                Client.log(CHOSEN_BITRATE_STRING + decision.bitrate);
                Client.log("Chosen number of segments: " + decision.numberOfSegment);

                //finish downloading
                Scanner reader = new Scanner(System.in);
                Client.log("Input round trip time");
                rtt = reader.nextLong();
                Client.log("Input download time");
                //TODO: tính cái này = Java luôn?
                downloadTime = reader.nextLong();
                Client.log("Input segment download time");
                reader.nextLine(); //consume eol from nextLong()
                String segmentTimes = reader.nextLine();
                String[] times = segmentTimes.split(", ");
                //TODO: array create 1 time
                segmentDownloadTimes = new ArrayList<>(times.length);
                for (int i = 0; i < times.length; i++) {
                    segmentDownloadTimes.add(Long.parseLong(times[i]));
                    System.out.println("hehe segment " + i + " down time: " + times[i]);
                }
                Client.log("Input content length");
                size = reader.nextInt();
            } else {
                httpFake.download(decision.numberOfSegment, decision.bitrate);
                rtt = httpFake.getRtt();
                size = httpFake.getContentLength();
                delayBeforeDown = httpFake.getDelay();
                downloadTime = httpFake.getDownloadTime();
                segmentDownloadTimes = httpFake.getSegmentTimes();
            }
            long tempFinishTime = httpFake == null ? System.currentTimeMillis() : httpFake.getCurrentTimeInMilis();
            //CY: downloadtime + delay time +time code linh tinh = elapsedTime (mình trừ đi trong buffer)
            buffer.decreaseBufferSize(tempFinishTime - lastFinishTime);
            long playTimeDownloaded = Manifest.REAL_SEGMENT_DURATION * decision.numberOfSegment;
            timeDownloaded += playTimeDownloaded;
            buffer.increaseBufferSize(playTimeDownloaded);
            lastFinishTime = tempFinishTime;

            info.put("finishTime", (lastFinishTime - startTime) / 1000.0);
            //byte *8 /1000 = kilo bit, downloadTime (milisecond) /1000 = second ====> Kbps kilobit per second
            info.put("throughput", (double) (size * decision.numberOfSegment * 8) / (downloadTime + rtt));
            info.put("bitrate", (double) decision.bitrate);
			info.put("buffer", buffer.getBufferSize() / 1000.0);
            info.put("numberOfSegments", (double) decision.numberOfSegment);            
            info.put("size", (double) size);
            info.put("rtt", rtt / 1000.0);
            info.put("delay", delayBeforeDown / 1000.0);
            info.put("downtime", downloadTime / 1000.0);
            info.put("last-sm-time", segmentDownloadTimes.get(segmentDownloadTimes.size() - 1) / 1000.0);
            adaptationLogic.writeExcelInfo(info);
            adaptationLogic.updateDownloadInfo(downloadTime, segmentDownloadTimes, rtt, size, decision.numberOfSegment);
            excelExporter.updateExportSheet(info.values());
            
        }
        
        printSegmentThroughput(true);
        
        //empty chosen bitrate, to notify the client that we have finished
        Client.log(CHOSEN_BITRATE_STRING);
        //pass the titles to the excel file
        excelExporter.writeAndOpen(info.keySet());
    }
    public void printSegmentThroughput(boolean isPrint){
    	if(isPrint){
    		try{
    			System.out.println("thangvu");
    			PrintWriter pw = new PrintWriter(new File ("Output\\SegmentThroughput.txt"));
    			for(int i = 0; i < httpFake.segmentThrpList.size();i++){
    				pw.println(httpFake.segmentThrpList.get(i).time +"\t"+ httpFake.segmentThrpList.get(i).throughput);
    			}
    			pw.close();
    		}catch(FileNotFoundException e){
    			System.err.println("file not found!");
    		}
    		
    	}
    }
}

