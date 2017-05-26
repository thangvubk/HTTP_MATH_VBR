package Client;
import java.io.IOException;

import Generator.GenerateScript;
import logic.AdaptationLogic;
import logic.SDPAlgorithm;

public class Client {
    //Change log:
    private static final boolean USE_FAKE_HTTP = true;

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        ExcelExporter excelExporter = new ExcelExporter();
        double maxDurationInSeconds = GenerateScript.createScript(Manifest.SERVER_ADDRESS, "VBR\\bandwidth.txt", 0, 599);

        CmdExec cmdExec;
        if (!USE_FAKE_HTTP) {
            cmdExec = new CmdExec();
            //regular, non-daemon thread
            cmdExec.start();
            Thread.sleep(800);
        }
        Buffer buffer = new Buffer(true);
        // AdaptationLogic adaptationLogic = new NewAdaptiveAlgorithm(buffer, new Manifest());
//       AdaptationLogic adaptationLogic = new TradeOffAlgorithm(buffer, new Manifest());
        //AdaptationLogic adaptationLogic = new AggressiveAlgorithm(buffer, new Manifest());
        //AdaptationLogic adaptationLogic = new DucMethodAlgorithm(buffer, new Manifest());
		//AdaptationLogic adaptationLogic = new ThangAlgorithm(buffer, new Manifest());
        //AdaptationLogic adaptationLogic = new TrellisHTTP2(buffer, new Manifest());
        
        AdaptationLogic adaptationLogic = new SDPAlgorithm(buffer, new Manifest());
        
		//AdaptationLogic adaptationLogic = new ThangCostFunctionAlgorithm(buffer, new Manifest());
        //AdaptationLogic adaptationLogic = new ThangVariableThresholdAlgorithm(buffer, new Manifest());
//        AdaptationLogic adaptationLogic = new Minh1AdaptiveAlgorithm(buffer, new Manifest());
        //-2 for avoid bitrate comes to infinity at the end //TODO: thuc ra -2 ko co y nghia j lam vi thuc te no lech kha nhieu (>10s)
        Thread downloaderThread = new Thread(new Downloader(excelExporter, buffer, adaptationLogic, (maxDurationInSeconds - 2) * 1000, USE_FAKE_HTTP));

        downloaderThread.start();
        // Finish working
        downloaderThread.join();
        if (!USE_FAKE_HTTP) {
            cmdExec.interrupt();
            cmdExec.join();
        }
        log("Finish everything");
    }

    public static void log(String text) {
        System.out.println("______________________________________________________________  " + text);
    }
}


