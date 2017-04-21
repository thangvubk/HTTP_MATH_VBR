package http1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;
    public final String hostName;
    private final int PORT = 80;
    public InputStream inputStream;
    private OutputStreamWriter outputStreamWriter;
    private static final String ENCODING = "UTF-8";

    public ClientSocket(String hostName) {
        this.hostName = hostName;
        try {
            socket = new Socket(hostName, PORT);
            //CY: this seems to have no effect because the default timeout is 2 hours https://answers.yahoo.com/question/index?qid=20080820100653AAbqyhM (this is just transport layer, instead normally you need smaller interval. In HTTP 1.1 there is a header "keep-alive" and we have used that. Anyway, our bandwidth trace is no more than 1600 second and we just close the socket afterwards so socket.setKeepAlive(true); is not needed. http://stackoverflow.com/questions/1480236/does-a-tcp-socket-connection-have-a-keep-alive
//            socket.setKeepAlive(true);

            //It's useful to disable the use of Nagle's algorithm (setTcpNoDelay(true)) when your communication over that socket comprises small packets and you want to transfer the data immediately
            //CY: setTCPNoDelay(true) (send data immediately) is contrast with using a buffer (wait until buffer is full). http://stackoverflow.com/questions/11373259/sockets-bufferedoutputstream-or-just-outputstream Using buffered stream: using a buffer, data is transfered only when buffer is full or when we manually flush it (maybe latency is high). thực ra ko hiểu để immediately làm cái j`, tốn CPU mà có khác j` mấy đâu
            //http://stackoverflow.com/questions/7443411/outputstreamwriter-flush-delay

            socket.setTcpNoDelay(true);
            System.out.println("Socket created! Host: " + hostName + "; port: " + PORT + "; LocalHost: " + socket.getLocalPort());
            inputStream = socket.getInputStream();
//            inputStream = new BufferedInputStream(socket.getInputStream());

            //http://stackoverflow.com/questions/17440795/send-a-string-instead-of-byte-through-socket-in-java : we dont want default encoding here
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOutputStream(String text) throws IOException {
        outputStreamWriter.write(text);
    }

    public void flushOutputStream() throws IOException {
        outputStreamWriter.flush();
    }

    public void close() {
        try {
            inputStream.close();
            outputStreamWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
