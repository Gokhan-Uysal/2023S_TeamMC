package app.ui.views.game.player;

import javax.swing.*;
import java.awt.*;

public class PlayerStatePanel extends JPanel {

    private int cornerRadius = 50;
    public JButton nextButton;
    public JLabel nameField;
    public JButton indicator1, indicator2, indicator3;


    public PlayerStatePanel(){
        super();
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(400, 250));
        setBackground(Color.CYAN);
        setForeground(Color.black);

        buildPanel();

    }


    public void buildPanel(){
        nextButton = new JButton("Next");
        nameField = new JLabel("AMK");

        indicator1 = new JButton("Sa");
        indicator2 = new JButton("As");
        indicator3 = new JButton("SaAS");

        indicator1.setBackground(Color.GREEN);
        indicator2.setBackground(Color.GRAY);
        indicator3.setBackground(Color.GRAY);

        this.add(nextButton);
        this.add(nameField);
        //this.add(indicator1);
        //this.add(indicator2);
        //this.add(indicator3);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}
