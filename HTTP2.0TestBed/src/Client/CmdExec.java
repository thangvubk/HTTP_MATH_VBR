package Client;

import Generator.GenerateScript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdExec extends Thread {

    public void run() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(GenerateScript.getRunScript());
            //Redirect for seeing realtime of verbose log
            processBuilder.redirectErrorStream(true);
            Process scriptProcess = processBuilder.start();

            Client.log("SCRIPT FILE START!");

            try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(scriptProcess.getInputStream()))) {
                String s;
                while (!isInterrupted() && (s = stdInput.readLine()) != null) {
                    Client.log(s);
                }
            }
            scriptProcess.destroy();
            Client.log("SCRIPT FILE FINISHED!");

            //flush all dummynet config
            new ProcessBuilder(GenerateScript.getFlushScript()).start();
            Client.log("DUMMYNET CONFIG FLUSHED");
        } catch (IOException e) {
            Client.log("SCRIPT FILE EXCEPTION:");
            e.printStackTrace();
        }
    }

}
