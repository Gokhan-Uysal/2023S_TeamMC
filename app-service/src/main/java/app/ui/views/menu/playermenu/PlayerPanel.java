package app.ui.views.menu.playermenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PlayerPanel extends JPanel {

    private int cornerRadius = 30;
    public JButton submitButton;
    public JTextField nameField;
    private JCheckBox checkBox;

    public PlayerPanel(int x, int y) {
        super();
        setOpaque(false);
        setLayout(null);
        setBounds(x, y, 360, 100);
        setBackground(Color.CYAN);
        setForeground(Color.black);
        BuildPanel(x, y);
    }

    public PlayerPanel(int cornerRadius) {
        super();
        setOpaque(false);
        this.cornerRadius = cornerRadius;
    }

    public void BuildPanel(int x, int y) {
        submitButton = new JButton("Submit");
        nameField = new JTextField("Enter your name");
        checkBox = new JCheckBox();

        submitButton.setBounds(5, 35, 80, 30);
        submitButton.setVisible(true);

        nameField.setBounds(100, 30, 200, 40);

        checkBox.setBounds(320, 0, 100, 100);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBox.isSelected()) {
                    nameField.setEnabled(false);
                    submitButton.setEnabled(false);
                } else {
                    nameField.setEnabled(true);
                    submitButton.setEnabled(true);
                }
            }
        });

        this.add(submitButton);
        this.add(nameField);
        this.add(checkBox);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}
