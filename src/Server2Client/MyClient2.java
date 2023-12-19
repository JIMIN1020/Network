package Server2Client;

import java.io.*;
import java.net.Socket;

// server to client
public class MyClient2 {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("172.20.28.35", 8188);

        InputStream istream = sock.getInputStream();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(istream));

        String s1 = br1.readLine();
        System.out.println(s1);

        br1.close();
        istream.close();
        sock.close();
    }
}
