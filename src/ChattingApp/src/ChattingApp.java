import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class ChattingApp {
    JLabel title, subTitle, desc;
    JLabel icon = new JLabel();
    JToggleButton t1, t2;
    JButton btn;
    JPanel p1, p2, p3, p4;
    ButtonGroup btnGroup = new ButtonGroup();
    JToggleButton homeToggleButton;

    ListPanel listPanel;
    MyPanel myPanel;
    FormPanel formPanel;
    ChatRoom chatRoom = new ChatRoom();

    public ChattingApp()
    {
        /* ------- frame ------- */
        JFrame jf = new JFrame("물품 대여 서비스");
        jf.getContentPane().setLayout(new FlowLayout());
        jf.getContentPane().setBackground(Color.WHITE);

        formPanel = new FormPanel(this);
        listPanel = new ListPanel(this);
        myPanel = new MyPanel(this);

        /* ------- 앱 타이틀 ------- */
        title = new JLabel("물품 대여 서비스");
        title.setFont(new Font("Noto sans KR", Font.BOLD, 24));
        title.setPreferredSize(new Dimension(400, 30));
        title.setHorizontalAlignment(JLabel.CENTER);

        /* ------- 명신관 라벨 ------- */
        JLabel myungshinLabel = new JLabel("명신관");
        myungshinLabel.setFont(new Font("Noto sans KR", Font.PLAIN, 16));

        /* ------- Home 토글 버튼 ------- */
        homeToggleButton = new JToggleButton("Home");
        homeToggleButton.setPreferredSize(new Dimension(80, 30));
        homeToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p3.removeAll();
                p3.add(icon, BorderLayout.NORTH);
                p3.add(desc, BorderLayout.SOUTH);
                p3.revalidate();
                p3.repaint();
            }
        });

        /* ------- 대여/MY 토글 버튼 ------- */
        t1 = new JToggleButton("대여");
        t2 = new JToggleButton("MY");
        btnGroup.add(t1);
        btnGroup.add(t2);

//        // 크기 및 스타일 수정
//        t1 = new JToggleButton("대여");
//        t2 = new JToggleButton("MY");
//        btnGroup.add(t1);
//        btnGroup.add(t2);
//
//        // 크기 및 스타일 수정
//        Font buttonFont = new Font("Noto sans KR", Font.BOLD, 14);
//        t1.setFont(buttonFont);
//        t1.setBackground(Color.decode("#e6eef4"));
//
//        t2.setFont(buttonFont);
//        t2.setBackground(Color.decode("#e6eef4"));

        /* ------- 버튼 event ------- */
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.isSelected()) {
                    p3.removeAll();
                    p3.add(listPanel);
                    p3.revalidate();
                    p3.repaint();
                } else {
                    p3.removeAll();
                    p3.add(icon, BorderLayout.NORTH);
                    p3.add(desc, BorderLayout.SOUTH);
                    p3.revalidate();
                    p3.repaint();
                }
            }
        });

        /* ------- 버튼 event ------- */
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t2.isSelected()) {
                    p3.removeAll();
                    p3.add(myPanel);
                    p3.revalidate();
                    p3.repaint();
                } else {
                    p3.removeAll();
                    p3.add(icon, BorderLayout.NORTH);
                    p3.add(desc, BorderLayout.SOUTH);
                    p3.revalidate();
                    p3.repaint();
                }
            }
        });

        /* ------- 로고 이미지 ------- */
        icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sookmyung.png")));
        icon.setPreferredSize(new Dimension(400, 400));
        icon.setHorizontalAlignment(JLabel.CENTER);


        /* ------- 하단 + 버튼 ------- */
        btn = new JButton("+");
        desc = new JLabel("하단 버튼을 클릭하면 대여폼 입력이 가능합니다.");
        desc.setHorizontalAlignment(JLabel.CENTER);
        desc.setPreferredSize(new Dimension(400, 30));

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p3.removeAll();
                p3.add(formPanel);
                p3.revalidate();
                p3.repaint();
            }
        });

        /* ------- 타이틀 패널 ------- */
        p1 = new JPanel(new BorderLayout());
        p1.setBackground(Color.WHITE);
        p1.setPreferredSize(new Dimension(400, 80));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(title);

        // Subtitle Panel
        JPanel subtitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        subtitlePanel.setBackground(Color.WHITE);
        subtitlePanel.add(Box.createVerticalStrut(10));
        subtitlePanel.add(myungshinLabel);
        subtitlePanel.add(homeToggleButton);

        p1.add(titlePanel, BorderLayout.NORTH);
        p1.add(subtitlePanel, BorderLayout.SOUTH);

        /* ------- 토글 버튼 패널 ------- */
        p2 = new JPanel();
        p2.setBackground(Color.WHITE);
        p2.add(t1);
        p2.add(t2);
        p2.setPreferredSize(new Dimension(400, 30));

        /* ------- 로고 이미지 패널 ------- */
        p3 = new JPanel(new BorderLayout());
        p3.setBackground(Color.WHITE);
        p3.setPreferredSize(new Dimension(400, 450));
        p3.add(icon, "North");
        p3.add(desc, "South");

        /* ------- 하단 버튼 패널 ------- */
        p4 = new JPanel();
        p4.setBackground(Color.WHITE);
        p4.add(btn);

        /* ------- 프레임에 패널 부착 ------- */
        jf.getContentPane().add(p1);
        jf.getContentPane().add(p2);
        jf.getContentPane().add(p3);
        jf.getContentPane().add(p4);

        jf.setSize(400, 650);
        jf.setVisible(true);

        formPanel.setFormSubmissionListener(myPanel);

        // 폼이 제출되면 MyPanel 인스턴스에 아이템을 추가하는 부분을 확인하세요.
        formPanel.setFormSubmissionListener(new FormPanel.FormSubmissionListener() {
            @Override
            public void onFormSubmit(String item, String time) {
                // MyPanel에 아이템 추가
                myPanel.updateRentalList(item, time);

                // ListPanel에도 필요하다면 아이템 추가
                listPanel.addItemToList(item, time);
            }
        });
    }
    public void showList() {
        p3.removeAll();
        p3.add(listPanel);
        p3.revalidate();
        p3.repaint();
    }

    public void showChatRoom() {
        p3.removeAll();
        p3.add(chatRoom);
        p3.revalidate();
        p3.repaint();
    }
}