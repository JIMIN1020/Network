import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatRoom extends JPanel {
    private static final String SERVER_IP = "172.20.9.65";
    private static final int SERVER_PORT = 5002;

    JPanel chatPanel, inputPanel, headerPanel;
    Color backgroundColor = Color.WHITE;
    Color lineColor = Color.DARK_GRAY;
    JTextField input;
    JButton send;
    JLabel providerLabel;

    Color color = new Color(235, 240, 255);
    JTextArea chat;
    Socket socket;

    public ChatRoom() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        // 상단 패널에 물품 제공자 정보 표시
        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createDashedBorder(lineColor));
        providerLabel = new JLabel("<html>물품 제공자: 김눈송<br>대여 물품: 아이패드 충전기<br>제공자 위치: 명신관 505호</html>");
        headerPanel.add(providerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // 중앙 채팅 내용 표시
        chat = new JTextArea(30, 80);

        // 하단 패널에 입력창과 전송 버튼 표시
        input = new JTextField(20);
        send = new JButton("send");

        inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(input);
        inputPanel.add(send);
        inputPanel.setBackground(backgroundColor);
        inputPanel.setPreferredSize(new Dimension(400, 100));


        input.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                char keyCode = e.getKeyChar();
                if (keyCode == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        add(chat, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 480));

        String name = "박눈송";
        socket = new Socket();  // Initialize the socket

        try {
            socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "join:" + name + "\r\n";
            pw.println(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new ChatClientReceiveThread(socket).start();  // Move this line after connecting the socket

    }

    private void sendMessage() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String message = input.getText();
            String request = "message:" + message + "\r\n";
            pw.println(request);

            input.setText("");
            input.requestFocus();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ChatClientReceiveThread extends Thread{
        Socket socket = null;

        ChatClientReceiveThread(Socket socket){
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                while(true) {
                    String msg = br.readLine();
                    chat.append(msg);
                    chat.append("\n");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

