package Client2Server;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// client to server
public class MyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket sersock = new ServerSocket(8988);
        System.out.println("server is ready!"); // message to know the server is running

        Socket sock = sersock.accept(); // accept the client

        // input data
        InputStream istream = sock.getInputStream();
        DataInputStream dstream = new DataInputStream(istream);

        String message2 = dstream.readLine();
        System.out.println(message2);
        dstream.close(); istream.close(); sock.close(); sersock.close();
    }
}
