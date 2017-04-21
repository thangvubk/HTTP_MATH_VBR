package Generator;

import java.io.*;
import java.util.ArrayList;

public class GenerateScript {
    //if set to true, this will auto generate the script to cover from start to finish of the smoothed range (LOWEST_TIME and HIGHEST_TIME is therefore irrelevant)

    //LOWEST TIME vÃ  HIGHEST time lÃ  time thá»±c táº¿ (double) á»Ÿ trong file áº¥y, chá»© khÃ´ng pháº£i lÃ  thá»© tá»± trong array.
    //CY: trong khoáº£ng tá»« 712 Ä‘áº¿n 716: time trÃ¹ng, tá»©c lÃ  bandwidth thay Ä‘á»•i láº­p tá»©c luÃ´n
    //-1 means lowest possible and highest possible
    private static final int SMOOTH_SECONDS = 1;
    /**
     * CY: cÃ¡i delay nÃ y chÃ­nh lÃ  config round trip time á»Ÿ trong ipfw. VÃ¬ mÃ¬nh dÃ¹ng mÃ¡y áº£o, nÃªn propagation delay thá»±c sá»± sáº½ cá»±c nhanh, ping thá»­ chá»‰ cÃ³ 0.mÆ°á»�i máº¥y millis. Config cÃ¡i nÃ y thÃ¬ sáº½ lÃ m cho round trip time tháº­t hÆ¡n (thá»±c táº¿ khoáº£ng <20ms cáº£ Ä‘i cáº£ vá»�). thá»±c táº¿ cÃ¡i mÃ  mÃ¬nh Ä‘o Ä‘Æ°á»£c áº¥y, nÃ³ lÃ  tÃ­nh tá»« lÃºc bit (hoáº·c byte) Ä‘áº§u tiÃªn Ä‘i ra, chá»� 1 Ã­t, bit cuá»‘i cÃ¹ng Ä‘i ra, chá»� 2xpropagation delay (cáº£ Ä‘i cáº£ vá»�). Ä�áº¿n khi nháº­n Ä‘Æ°á»£c cÃ¡i byte Ä‘áº§u tiÃªn rá»“i, thÃ¬ Ä‘Ã¡nh dáº¥u Ä‘iá»ƒm cuá»‘i. CÃ³ nghÄ©a lÃ  = delay nÃ y *2 + (request size+ 1 byte)/bandwidth bandwidth lÃ  váº­n tá»‘c, láº¥y sá»‘ byte chia cho váº­n tá»‘c ta Ä‘Æ°á»£c thá»�i gian. NhÆ°ng thÆ°á»�ng thÃ¬ request size ráº¥t nhá»� so vs tá»‘c Ä‘á»™ (bandwidth) nÃªn cÃ¡i Ä‘Ã³ coi nhÆ° = 0, cho nÃªn coi nhÆ° cÃ¡i round trip time cá»§a mÃ¬nh Ä‘o Ä‘Æ°á»£c chÃ­nh lÃ  round trip time (2*propagation delay) tháº­t. Náº¿u request size to (gá»“m nhiá»�u packet cháº³ng háº¡n) thÃ¬ thá»�i gian truyá»�n cÃ¡i Ä‘Ã³ sáº½ Ä‘Ã¡ng ká»ƒ, vÃ  round trip time Ä‘o Ä‘Æ°á»£c sáº½ lá»›n hÆ¡n 2* propagation delay 1 khoáº£ng Ä‘Ã¡ng ká»ƒ.
     * <p>
     * thá»±c táº¿ thÃ¬ ko pháº£i lÃºc nÃ o cÅ©ng Ä‘Ãºng lÃ  con sá»‘ nÃ y Ä‘Ã¢u, 90% lÃ  tháº¿ nÃ y, nhÆ°ng cÃ³ nhá»¯ng lÃºc phá»�t lÃªn rá»“i láº¡i xuá»‘ng ráº¥t nhanh (spikes)
     * <p>
     * delay 50ms, thi throughput anh huong: giáº£ sá»­ ban Ä‘áº§u cÃ¹ng chá»�n 1 bitrate: thÃ¬ segment 4 sáº½ cÃ³ throughput > segment 1 , do Ä‘Ã³ sau Ä‘Ã³ nÃ³ sáº½ Æ°u tiÃªn chá»�n bitrate cao hÆ¡n segment 1... vÃ  do Ä‘Ã³ se co bitrate trung binh lá»›n hÆ¡n segment 1, vÃ  tá»•ng lÆ°á»£ng data sáº½ lá»›n hÆ¡n.
     */
    private static final String INPUT_PATH = "Input" + File.separator;
    public static final String OUTPUT_DIR = "Output";
    private static final String OUTPUT_FILE_NAME = "dummynet_script";

    private static final String SCRIPT_FILE_PATH = OUTPUT_DIR + File.separator + OUTPUT_FILE_NAME + (isWindows() ? ".bat" : ".sh");
    public static final String TEXT_FILE_PATH = OUTPUT_DIR + File.separator + OUTPUT_FILE_NAME + ".txt";
    private static final String FLUSH_SCRIPT_FILE_PATH = OUTPUT_DIR + File.separator + "flush_dummynet_script" + (isWindows() ? ".bat" : ".sh");
    public static final int PROPAGATION_DELAY_IN_MILIS = 50;

    public static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Window");
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        createScript("myserver", "trace1.txt", -1, -1);
    }

    /**
     * @return the max duration in the script (in seconds)
     */
    public static double createScript(String serverAddress, String fileName, int LOWEST_TIME, int HIGHEST_TIME) throws NumberFormatException, IOException {
        ArrayList<Double> rawBandwidth = new ArrayList<>();
        ArrayList<Double> timeStamp = new ArrayList<>();
        //read input from bandwidth trace file
        try (BufferedReader inStream = new BufferedReader(new FileReader(INPUT_PATH + fileName))) {
            String currentLine;
            while ((currentLine = inStream.readLine()) != null) {
                timeStamp.add(Double.parseDouble(currentLine.split("\t")[0]));
                rawBandwidth.add(Double.parseDouble(currentLine.split("\t")[1]));
            }
        }

        int[] bandwidth = new int[rawBandwidth.size()];
        int leftMargin = (SMOOTH_SECONDS - 1) / 2;
        int rightMargin = SMOOTH_SECONDS / 2;
        int startSmoothRangeIndex = leftMargin;
        int finishSmoothRangeIndex = rawBandwidth.size() - 1 - rightMargin;

        if (SMOOTH_SECONDS > 1) {
            //only allow the user to select the time range inside the smoothed time range
            if (LOWEST_TIME != -1 && (LOWEST_TIME < timeStamp.get(startSmoothRangeIndex))) {
                throw new RuntimeException("With SMOOTH_SECONDS = " + SMOOTH_SECONDS + ", LOWEST_TIME must >= " + timeStamp.get(startSmoothRangeIndex));
            }
            if (HIGHEST_TIME != -1 && (HIGHEST_TIME > timeStamp.get(finishSmoothRangeIndex))) {
                throw new RuntimeException("With SMOOTH_SECONDS = " + SMOOTH_SECONDS + ", HIGHEST_TIME must <= " + timeStamp.get(finishSmoothRangeIndex));
            }
//        System.out.println("temp: " + leftMargin);
            for (int i = startSmoothRangeIndex; i <= finishSmoothRangeIndex; i++) {
//                System.out.println("Start outer loop: i: " + i);
                double total = 0;
                for (int j = i - leftMargin; j <= i + rightMargin; j++) {
                    total += rawBandwidth.get(j);
                    //                System.out.println("\t RealBW[" + j + "] =" + rawBandwidth.get(j) + ", total = " + total);
                }
                bandwidth[i] = (int) (total / SMOOTH_SECONDS);
                //            System.out.println("Finish outer loop i = " + i + ", smoothBW[" + i + "]= " + bandwidth[i]);
            }
        } else {
            //simply copy the array
            for (int i = 0; i < rawBandwidth.size(); i++) {
                bandwidth[i] = (int) ((double) rawBandwidth.get(i));
            }
        }

        //finish convert the whole input bandwidth into a smoothed bandwidth list
        //simulate the smoothed bandwidth.

        boolean isWindows = isWindows();

        try (PrintWriter outScriptStream = new PrintWriter(SCRIPT_FILE_PATH);
             PrintWriter outTxtStream = new PrintWriter(TEXT_FILE_PATH);
             PrintWriter outFlushScriptStream = new PrintWriter(FLUSH_SCRIPT_FILE_PATH)) {

            if (isWindows) {
                outScriptStream.println("@echo on");
                //nodosfilewarning: do not warn about DOS path (not UNIX path)
                outScriptStream.println("@set CYGWIN=nodosfilewarning");
            } else {
                //unix-like OS
                //unix-like script files should always be started with a shebang. This shebang means bash should be started from anywhere in the system
                String SHEBANG = "#!/usr/bin/env bash";
                outScriptStream.println(SHEBANG);
                outFlushScriptStream.println(SHEBANG);
                outScriptStream.println("set -o verbose");
            }
            //-q: quiet
            //delete all rules
            //@ sign is used for not echoing the current command (in batch file)
            String ipfwCommand = (isWindows ? "ipfw" : "sudo ipfw") + " ";
            String flushScript = (isWindows ? "@" : "") + ipfwCommand + "-q flush" + "\n" + (isWindows ? "@" : "") + ipfwCommand + "-q pipe flush";
            outScriptStream.println(flushScript);
            outFlushScriptStream.println(flushScript);

            //force all connections to go through this pipe
            outScriptStream.println(ipfwCommand + "add pipe 3 ip from me to " + serverAddress);
            outScriptStream.println(ipfwCommand + "add pipe 3 ip from " + serverAddress + " to me");
            outScriptStream.println();

            int startIndex;
            if (LOWEST_TIME != -1) {
                startIndex = 0;
                //only control when the time range is inside our specified time range
                while (timeStamp.get(startIndex) < LOWEST_TIME) startIndex++;
            } else {
                startIndex = startSmoothRangeIndex;
            }

            int finishIndex;
            if (HIGHEST_TIME != -1) {
                finishIndex = startIndex;
                while (timeStamp.get(finishIndex) <= HIGHEST_TIME) finishIndex++;
                finishIndex--;
            } else {
                finishIndex = finishSmoothRangeIndex;
            }

            for (int i = startIndex; i <= finishIndex; i++) {
                // skip those which have the same timestamp
                if (!(i < finishIndex && timeStamp.get(i + 1) <= timeStamp.get(i))) {
                    outScriptStream.println(ipfwCommand + "pipe 3 config bw " + bandwidth[i] + "Kbit/s delay " + PROPAGATION_DELAY_IN_MILIS + "ms");
                    int delayInMilis = i > 0 ? (int) ((timeStamp.get(i) - timeStamp.get(i - 1)) * 1000) : 1000;
                    if (isWindows) {
//                    You can ping an address that surely doesn't exist and specify the desired timeout for waiting
//                The -w 10000 part specifies the desired timeout in milliseconds.
//                        The -n 1 part tells ping that it should only tries once (normally it'd try 4 times).
//                The > nul part is appended so the ping command doesn't output anything to screen.
                        //In short: delay 1000ms
                        outScriptStream.println("ping 1.1.1.1 -n 1 -w " + delayInMilis + " >NUL");
                    } else {
                        //vÃ¬ cÃ³ nhá»¯ng cÃ¡i nhÆ° dÃ²ng 712 Ä‘áº¿n 716 nÃªn cÃ³ nhá»¯ng lÃºc sleep sáº½ chá»‰ lÃ  sleep 0. CÃ²n láº¡i thÃ¬ toÃ n sleep 1
                        outScriptStream.println("sleep " + delayInMilis / 1000.0);
                    }
                    outTxtStream.println(timeStamp.get(i) - timeStamp.get(startIndex) + "\t" + bandwidth[i]);
                }
            }
            return timeStamp.get(finishIndex) - timeStamp.get(startIndex);
        }
    }

    private static String[] getScript(String path) {
        String[] command;
        if (!isWindows()) {
            //Only works with Mac OS X with iTerm installed. create run script file, then this run script will invoke the script file. But if we do this, then CmdExec no longer control the running script.
//            String[] allCommands = new String[]{"osascript",
//                    "tell application \"iTerm\"",
//                    "make new terminal",
//                    "tell the current terminal",
//                    "activate current session",
//                    "launch session \"Default Session\"",
//                    "tell the last session",
//                    "write text \"cd " + System.getProperty("user.dir") + "; clear; sh " + SCRIPT_FILE_PATH + "\"",
//                    "end tell",
//                    "end tell",
//                    "end tell"};
//            command = new String[allCommands.length * 2 - 1];
//            for (int i = 0; i < allCommands.length - 1; i++) {
//                command[i * 2] = allCommands[i];
//                command[i * 2 + 1] = "-e";
//            }
//            command[command.length - 1] = allCommands[allCommands.length - 1];
            command = new String[]{"sh", path};
        } else {
            //CY: batch file chi hoat dong khi ko phai run as admin. Neu run as admin no se co start directory at C:\windows32.... Khi áº¥y thÃ¬ máº¥y cÃ¡i kiá»ƒu mkdir sáº½ ko cháº¡y Ä‘Æ°á»£c Ä‘Ãºng. NhÆ°ng á»Ÿ Ä‘Ã¢y mÃ¬nh chá»‰ dÃ¹ng ipfw nÃªn ko áº£nh hÆ°á»Ÿng
//            command = new String[]{"cmd", "/C", "start", path};
            command = new String[]{path};
        }
        return command;
    }

    public static String[] getRunScript() {
        return getScript(SCRIPT_FILE_PATH);
    }

    public static String[] getFlushScript() {
        return getScript(FLUSH_SCRIPT_FILE_PATH);
    }
}
