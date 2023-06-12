package app.ui.views.animations;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NumberAnimation extends SwingWorker<Void, Void> {
    private int number;
    private Point position;
    private JPanel panel;

    public NumberAnimation(int number, Point position, JPanel panel) {
        this.number = number;
        this.position = position;
        this.panel = panel;
    }

    @Override
    protected Void doInBackground() throws Exception {
        JLabel label = new JLabel(Integer.toString(number));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(position.x, position.y, 50, 30);

        panel.add(label);
        panel.setComponentZOrder(label, 0);
        panel.revalidate();
        panel.repaint();

        Thread.sleep(2000); // Display the number for 3 seconds

        panel.remove(label);
        panel.revalidate();
        panel.repaint();
        return null;
    }
}
