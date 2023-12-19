

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel{
    private JLabel q1, q2;
    private JTextField a1, a2;
    private JPanel mainPanel, p1, p2, p3;
    private JButton btn;
    private Color color = new Color(235, 240, 255);

    // 인터페이스 정의
    public interface FormSubmissionListener {
        void onFormSubmit(String item, String time);
    }

    private FormSubmissionListener submissionListener;

    // FormSubmissionListener를 등록하는 메서드
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.submissionListener = listener;
    }

    public FormPanel(ChattingApp app) {
        setLayout(new FlowLayout());

        /* ------- labels ------- */
        q1 = new JLabel("필요한 물품이 무엇인가요?");
        q1.setHorizontalAlignment(JLabel.CENTER);
        q2 = new JLabel("물품을 대여할 시간대를 입력해주세요.");
        q2.setHorizontalAlignment(JLabel.CENTER);

        /* ------- inputs ------- */
        a1 = new JTextField(20);
        a1.setPreferredSize(new Dimension(250, 50));
        a2 = new JTextField(20);
        a2.setPreferredSize(new Dimension(250, 50));

        /* ------- button ------- */
        btn = new JButton("제출");

        // 버튼 클릭 이벤트 처리
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // FormSubmissionListener를 통해 데이터 전달
                if (submissionListener != null) {
                    submissionListener.onFormSubmit(a1.getText(), a2.getText());
                }
                // 대여 리스트로 이동
                app.showList();
            }
        });

        /* ------- input 패널 ------- */
        p1 = new JPanel();
        p1.setBackground(color);
        p1.setLayout(new GridLayout(2, 1));
        p1.setPreferredSize(new Dimension(300, 100));
        p1.add(q1);
        p1.add(a1);

        p2 = new JPanel();
        p2.setBackground(color);
        p2.setLayout(new GridLayout(2, 1));
        p2.setPreferredSize(new Dimension(300, 100));
        p2.add(q2);
        p2.add(a2);

        /* ------- button 패널 ------- */
        p3 = new JPanel();
        p3.setBackground(color);
        p3.setPreferredSize(new Dimension(400, 50));
        p3.add(btn);
        p3.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        LineBorder b = new LineBorder(Color.red, 0, true);

        /* ------- 메인 패널 ------- */
        mainPanel = new JPanel(new FlowLayout());
        mainPanel.setPreferredSize(new Dimension(400, 350));
        mainPanel.setBorder(b);
        mainPanel.setBackground(color);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(p1);
        mainPanel.add(p2);
        mainPanel.add(p3);

        setBorder(BorderFactory.createEmptyBorder(50, 20, 0, 20));
        add(mainPanel);

        setVisible(true);
        setBackground(Color.white);
        setPreferredSize(new Dimension(300, 480));
    }
}