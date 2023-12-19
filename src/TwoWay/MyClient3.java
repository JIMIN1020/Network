package TwoWay;

import java.io.*;
import java.net.Socket;

public class MyClient3 {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("172.20.28.35", 8588);

        // reading the file name from keyboard. Using Input stream
        System.out.println("Enter the file name: ");
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = keyRead.readLine();

        // sending the fie name to server. Using PrintWriter
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        // receiving the contents from server. Using Input stream
        InputStream istream = sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));

        // reading line-by-line
        String str;
        while ((str = socketRead.readLine()) != null) {
            System.out.println("Received contents from server: " + str);
        }
        pwrite.close();
        socketRead.close();
        keyRead.close();
    }
}
