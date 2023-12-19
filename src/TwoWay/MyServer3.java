package TwoWay;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer3 {
    public static void main(String[] args) throws Exception {
        // establishing the connection with the server
        ServerSocket sersock = new ServerSocket(8588);
        System.out.println("Server ready for connection");
        Socket sock = sersock.accept();

        // reading the file name from client
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();

        // reading file contents
        BufferedReader contentRead = new BufferedReader(new FileReader(fname));

        // keeping output stream ready to send the contents
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        // reading line-by-line from file
        String str;
        while ((str = contentRead.readLine()) != null) {
            System.out.println("Received contents from client: " + str);
            System.out.println("Re-sending received contents " + str);
            pwrite.println(str); // sending each line to client
        }

        sock.close();
        sersock.close(); // closing network sockets
        pwrite.close();
        fileRead.close();
        contentRead.close();
    }
}
