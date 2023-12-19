
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  // Import ArrayList class
import java.util.Date;
import java.util.List;

public class MyPanel extends JPanel implements FormPanel.FormSubmissionListener {

    private JLabel title;
    private List<MyItemPanel> rentalItemList;
    private JPanel itemsPanel;
    ChattingApp app;

    public MyPanel(ChattingApp app) {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400, 480));
        setBackground(Color.WHITE);

        this.app = app;

        title = new JLabel("나의 대여 리스트");
        title.setFont(new Font("Noto sans KR", Font.BOLD, 22));
        title.setPreferredSize(new Dimension(400, 50));
        title.setHorizontalAlignment(JLabel.CENTER);

        rentalItemList = new ArrayList<>();
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        itemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        add(title);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void onFormSubmit(String item, String time) {
        updateRentalList(item, time);
    }

    public void updateRentalList(String item, String time) {
        MyItemPanel newItemPanel = new MyItemPanel(item, time, app);
        rentalItemList.add(newItemPanel);

        itemsPanel.removeAll();
        for (MyItemPanel rentalItem : rentalItemList) {
            itemsPanel.add(rentalItem);
            itemsPanel.add(Box.createHorizontalStrut(10));  // Add some spacing between items
        }

        revalidate();
        repaint();
    }

    class MyItemPanel extends JPanel {
        private JPanel p1, p2;
        private JLabel itemLabel, timeLabel;
        JButton btn;

        public MyItemPanel(String item, String time, ChattingApp app) {
            setLayout(new FlowLayout());
            setPreferredSize(new Dimension(300, 80));
            setBackground(new Color(235, 240, 255));
            setBorder(null); // Set the border to null

            p1 = new JPanel(new GridLayout(2, 1));
            p1.setBackground(new Color(235, 240, 255));
            p1.setBorder(null); // Set the border to null
            p1.setPreferredSize(new Dimension(200, 70));

            itemLabel = new JLabel("<html><b>물품:</b> " + item + "</html>");
            timeLabel = new JLabel("<html><b>대여일자:</b> " + getCurrentDateTime() + "</html>");

            p2 = new JPanel();
            p2.setBackground(new Color(235, 240, 255));
            btn = new JButton("채팅");
            p2.add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.showChatRoom();
                }
            });

            // Set label properties
            int fontSize = 14;
            itemLabel.setFont(itemLabel.getFont().deriveFont(Font.BOLD));
            timeLabel.setFont(timeLabel.getFont().deriveFont(Font.BOLD));
            itemLabel.setHorizontalAlignment(JLabel.CENTER);
            timeLabel.setHorizontalAlignment(JLabel.CENTER);

            p1.add(itemLabel);
            p1.add(timeLabel);

            add(p1); add(p2);

            // Set label colors
            itemLabel.setForeground(new Color(0, 0, 0)); // Black text color
            timeLabel.setForeground(new Color(0, 0, 0)); // Black text color
        }

        private String getCurrentDateTime() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            return dateFormat.format(currentDate);
        }
    }
} 