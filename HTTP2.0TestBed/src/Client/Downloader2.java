//package Client;
//
//import http1.Http1Client;
//import logic.AdaptationDecision;
//import logic.AdaptationLogic;
//
//import java.util.LinkedHashMap;
//import java.util.Scanner;
//
//public class Downloader2 implements Runnable {
//    private Buffer buffer;
//    private AdaptationLogic adaptationLogic;
//
//    private ExcelExporter excelExporter;
//    private LinkedHashMap<String, Double> info = new LinkedHashMap<>();
//    private static final String CHOSEN_BITRATE_STRING = "Chosen bitrate: ";
//    private final double MAX_DURATION;
//
//    public Downloader2(ExcelExporter excelExporter, Buffer buffer, AdaptationLogic adaptationLogic, double maxDurationInMillis) {
//        this.buffer = buffer;
//        this.adaptationLogic = adaptationLogic;
//        this.MAX_DURATION = maxDurationInMillis;
//        this.excelExporter = excelExporter;
//        excelExporter.initExportSheet();
//    }
//
//    @Override
//    public void run() {
//        //stop when downloaded the whole video or when finished dummynet script
//        long startTime = System.currentTimeMillis();
//        long lastFinishTime = startTime;
//        Http1Client client = new Http1Client(Manifest.SERVER_ADDRESS, (int) Manifest.REAL_SEGMENT_DURATION);
//        for (long timeDownloaded = 0; timeDownloaded < Manifest.VIDEO_DURATION && lastFinishTime - startTime < MAX_DURATION; ) {
//            // First, use the adaptation logic to get the url
//            System.out.println("\n\n");
//            System.out.println("************************** START DOWNLOAD SEGMENT: time downloaded: " + timeDownloaded / 1000.0 + ", time elapsed: " + (lastFinishTime - startTime) / 1000.0);
//            AdaptationDecision decision = adaptationLogic.decide();
//
//            //if buffer is larger than optimum, make it reduced to optimum
//            //we only delay when we have allowed Player to play
//            long delayBeforeDown = buffer.diffFromOptimum();
//            if (delayBeforeDown > 0) {
//                try {
//                    Thread.sleep(delayBeforeDown);
//                    //CY: after sleeping, you must immediately run the download
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                delayBeforeDown = 0;
//            }
//
//            //start downloading
//            client.download(decision.bitrate, decision.numberOfSegment);
//            long lastSegmentTime = client.lastSegmentTime;
//            long downloadTime = client.downloadTime;
//            long rtt = client.roundTripTime;
//            int size = client.contentLength;
//
//            long tempFinishTime = System.currentTimeMillis();
//            //CY: downloadtime + delay time +time code linh tinh = elapsedTime (mình trừ đi trong buffer)
//            buffer.decreaseBufferSize(tempFinishTime - lastFinishTime);
//            long playTimeDownloaded = Manifest.REAL_SEGMENT_DURATION * decision.numberOfSegment;
//            timeDownloaded += playTimeDownloaded;
//            buffer.increaseBufferSize(playTimeDownloaded);
//            lastFinishTime = tempFinishTime;
//
//            info.put("finishTime", (lastFinishTime - startTime) / 1000.0);
//            //byte *8 /1000 = kilo bit, downloadTime (milisecond) /1000 = second ====> Kbps kilobit per second
//            info.put("throughput", (double) (size * decision.numberOfSegment * 8) / (downloadTime + rtt));
//            info.put("bitrate", (double) decision.bitrate);
//            info.put("numberOfSegments", (double) decision.numberOfSegment);
//            info.put("buffer", buffer.getBufferSize() / 1000.0);
//            info.put("size", (double) size);
//            info.put("rtt", rtt / 1000.0);
//            info.put("delay", delayBeforeDown / 1000.0);
//            info.put("downtime", downloadTime / 1000.0);
//            info.put("last-sm-time", lastSegmentTime / 1000.0);
//            adaptationLogic.updateDownloadInfo(downloadTime, lastSegmentTime, rtt, size, decision.numberOfSegment);
//            excelExporter.updateExportSheet(info.values());
//        }
//        //empty chosen bitrate, to notify the client that we have finished
//        //pass the titles to the excel file
//        excelExporter.writeAndOpen(info.keySet());
//    }
//}
//
