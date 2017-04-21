package http1;

import java.util.Scanner;

/**
 * Created by phanhaiphong on 6/25/15.
 */
public class ClientRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input server address");
        String host = scanner.next();
        scanner.nextLine();
        System.out.println("Input segment duration");
        int sd = scanner.nextInt();
        scanner.nextLine();
        Http1Client client = new Http1Client(host, sd);
//        Http1Client client = new Http1Client("myserver", 1000);

        String bitrateAnswer = askBitrate(scanner);
        while (!bitrateAnswer.isEmpty()) {
            int bitrate = Integer.parseInt(bitrateAnswer);
            System.out.println("Input number of segments");
            int no = scanner.nextInt();
            scanner.nextLine();
            client.download(bitrate, no);
            bitrateAnswer = askBitrate(scanner);
        }
        System.out.println("Finish everything");
    }

    private static String askBitrate(Scanner scanner) {
        System.out.println("Input bitrate");
        return scanner.nextLine();
    }
}
