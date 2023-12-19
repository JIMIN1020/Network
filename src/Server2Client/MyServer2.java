package Server2Client;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

// server to client
public class MyServer2 {
    public static void main(String[] args) throws Exception {
        ServerSocket sersock = new ServerSocket(8188);
        System.out.println("Server is ready!");
        Socket sock = sersock.accept();

        OutputStream ostream = sock.getOutputStream();
        BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(ostream));
        String s2 = "Thanks client for your calling on " + new java.util.Date();
        bw1.write(s2);

        bw1.close();
        ostream.close();
        sock.close();
        sersock.close();
    }
}
