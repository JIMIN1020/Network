

import javax.swing.*;
import java.awt.*;


public class RoundedLabel extends JLabel  {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;

    public RoundedLabel(String text, int arcWidth, int arcHeight, Color backgroundColor, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);

        super.paintComponent(g2d);

        g2d.dispose();
    }
}
