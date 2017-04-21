package http1;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phanhaiphong on 6/25/15.
 */
public class Http1Client {
    private ClientSocket socket;
    private final int segmentDuration;
    private byte[] buf = new byte[1024];
    private StringBuilder totalResponse = new StringBuilder(270);
    private StringBuilder tempSequence = new StringBuilder(5);

    public long roundTripTime;
    public long downloadTime;
    public long lastSegmentTime;
    public int contentLength;

    public Http1Client(String hostName, int segmentDuration) {
        socket = new ClientSocket(hostName);
        this.segmentDuration = segmentDuration;
    }

    public void download(int bitrate, int numberOfSegments) {
        try {
            long startRequestTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfSegments; i++) {
                //CY: mỗi lần request là có 253-259 bytes response về //TODO: bảo minh
                sendGETRequest(bitrate);
            }

            //CY: code gốc là đánh dấu khi cái byte đầu tiên được chuyển về, còn ở đây mình đánh dấu rtt là kể từ sau khi down xong cái response header đầu tiên
            totalResponse.setLength(0);
            tempSequence.setLength(0);
            int sizeOfHeaderInBytes = 0;
            //http response is ended by "\r\n\r\n" (a blank line)
            while (!"\r\n\r\n".equals(tempSequence.toString())) {
                char data = (char) socket.inputStream.read();
                totalResponse.append(data);
                if (data == '\r' || data == '\n') {
                    //if this is \r or \n, then append this character to our existing trail of \r , \n chars. If we get a '\r\n\r\n' sequence, then it means we have reached the end of the header
                    tempSequence.append(data);
                } else {
                    //if this is not \r or \n, then make temp_char empty
                    tempSequence.setLength(0);
                }
                sizeOfHeaderInBytes++;
            }
            System.out.println("Header size: " + sizeOfHeaderInBytes + " bytes");
            System.out.println("Server response: " + totalResponse);

            Pattern MY_PATTERN = Pattern.compile("Content-Length: (\\d+)");
            Matcher m = MY_PATTERN.matcher(totalResponse);
            int segmentSize = -1;
            if (m.find()) {
                //always true, but we must call find()
                segmentSize = Integer.parseInt(m.group(1));
                System.out.println("Segment size is: " + segmentSize);
            }

            int downloadSize = numberOfSegments * segmentSize; //đây chỉ là dơnloadsize bề ngoài vậy thôi, thực tế phải + thêm header size (khoảng >250 bytes) * (numberOfSegments - 1)) CY!!!: nếu số bitrate của mình mà nhỏ, thì mấy cái số header size này sẽ đáng kể, và do đó sẽ cần đuợc tính vào khi ta tính throughput (4 request có tầm 1KB là response header). Còn nếu vẫn to thì ko cần tính

            long startReceiveTime = System.currentTimeMillis();
            int byteCount = 0;
            //-1 because we have already passed the first response header
            while (byteCount < downloadSize + sizeOfHeaderInBytes * (numberOfSegments - 1)) {
                byteCount += socket.inputStream.read(buf);
//                System.out.print("receive " + byteCount + ", ");
            }
//            System.out.println("receive finish");
            long finishReceiveTime = System.currentTimeMillis();

            roundTripTime = startReceiveTime - startRequestTime;
            downloadTime = finishReceiveTime - startReceiveTime;
            lastSegmentTime = 0;//TODO: làm
            contentLength = segmentSize;

            System.out.println("Round trip time: " + roundTripTime);
            System.out.println("Download time: " + downloadTime);
            //CY: qua test cho thấy: từng segment về từng thằng 1 lần lượt theo thứ tự
            System.out.println("Last segment time: " + lastSegmentTime);
            System.out.println("Content length: " + contentLength);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendGETRequest(int bitrate) throws IOException {
        socket.writeOutputStream("GET " + "http://" + socket.hostName + "/video/" + segmentDuration + "/" + bitrate + "/seg-1" + " HTTP/1.1\r\n");
        socket.writeOutputStream("User-Agent: HTTPClient\r\n");
        socket.writeOutputStream("Host: " + socket.hostName + "\r\n");
        socket.writeOutputStream("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");
        socket.writeOutputStream("Connection: keep-alive\r\n");
        socket.writeOutputStream("\r\n");
        socket.flushOutputStream();
    }

    public static void main(String[] args) {
//        Pattern MY_PATTERN = Pattern.compile("Content-Length: [0-9]+");
        Pattern MY_PATTERN = Pattern.compile("Content-Length: (\\d+)");
        Matcher m = MY_PATTERN.matcher("hehe Content-Length: 234 asdf");
        System.out.println("pattern: " + MY_PATTERN + ", found: " + m.find());
        System.out.println("length ne: " + m.group(1));
    }
}
