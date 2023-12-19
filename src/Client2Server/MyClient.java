package Client2Server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

// client to server
public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("172.20.28.35", 8988);
        String message1 = "Accept Best Wishes, My Server";

        OutputStream ostream = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(ostream);

        dos.writeBytes(message1);
        dos.close();
        ostream.close();
        sock.close();
    }
}
