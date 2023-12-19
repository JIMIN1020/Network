

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPanel extends JPanel {

    JLabel title;
    ListItem item;
    ChattingApp app;

    public ListPanel(ChattingApp app) {
        setLayout(new FlowLayout());
        this.app = app;
        item = new ListItem(app);

        title = new JLabel("대여 요청 리스트");
        title.setFont(new Font("Noto sans KR", Font.BOLD, 22));
        title.setPreferredSize(new Dimension(400, 50));
        title.setHorizontalAlignment(JLabel.CENTER);

        add(title);
        add(item);
        setVisible(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 480));
    }

    public void addItemToList(String itemText, String timeText) {
        // 새로운 ListItem 생성
        ListItem newItem = new ListItem(app);
        newItem.item.setText("대여 물품: " + itemText);
        newItem.time.setText("대여 시간: " + timeText);

        // ListPanel에 추가
        add(newItem);

        // 갱신
        revalidate();
        repaint();
    }

    class ListItem extends JPanel {

        JPanel p1, p2;
        JPanel title;
        JLabel item, time, userName;
        RoundedLabel status;
        JButton btn;
        Color color = new Color(235, 240, 255);

        public ListItem(ChattingApp app) {
            setLayout(new FlowLayout());

            /* ------- labels ------- */
            status = new RoundedLabel("매칭 전", 10, 10, new Color(255, 234, 173), SwingConstants.CENTER);
            status.setPreferredSize(new Dimension(40, 20));
            status.setBorder(BorderFactory.createEmptyBorder(0, 5 , 0, 5));
            userName = new JLabel("박눈송");
            userName.setBorder(BorderFactory.createEmptyBorder(0, 10 , 0, 0));
            item = new JLabel("대여 물품: 아이패드 충전기");
            time = new JLabel("대여 시간: 1:30 PM");

            title = new JPanel(new GridLayout(2, 1));
            title.setBackground(color);
            title.add(status);
            title.add(userName);
            title.add(new JLabel(""));

            /* ------- button ------- */
            btn = new JButton("수락");

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.showChatRoom();
                }
            });

            /* ------- 왼쪽 패널 ------- */
            p1 = new JPanel(new GridLayout(3, 1));
            p1.setBackground(color);
            p1.setPreferredSize(new Dimension(220, 70));
            p1.add(title);  p1.add(item);  p1.add(time);

            /* ------- 오른쪽 패널 ------- */
            p2 = new JPanel();
            p2.setBackground(color);
            p2.setPreferredSize(new Dimension(70, 70));
            p2.add(btn);

            add(p1);
            add(p2);

            setPreferredSize(new Dimension(300, 80));
            setBorder(BorderFactory.createEmptyBorder(5 , 0 , 5 , 0));
            setBackground(color);
            setVisible(true);
        }
    }

}
