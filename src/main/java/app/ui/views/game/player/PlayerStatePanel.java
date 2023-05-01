package app.ui.views.game.player;

import javax.swing.*;
import java.awt.*;

public class PlayerStatePanel extends JPanel {

    private int cornerRadius = 30;
    public JButton nextButton;
    public JLabel nameField;
    public JButton indicator1, indicator2, indicator3;


    public PlayerStatePanel(){
        super();
        setOpaque(false);
        setLayout(null);
        setBounds(580, 600, 360, 100);
        setBackground(Color.CYAN);
        setForeground(Color.black);

    }


    public void BuildPanel(String playerName){
        nextButton = new JButton("Next");
        nameField = new JLabel(playerName);

        indicator1 = new JButton();
        indicator2 = new JButton();
        indicator3 = new JButton();

        indicator1.setBackground(Color.GREEN);
        indicator2.setBackground(Color.GRAY);
        indicator3.setBackground(Color.GRAY);

        indicator1.setBounds(5, 35, 80, 30);
        indicator1.setVisible(true);

        indicator2.setBounds(5, 35, 80, 30);
        indicator2.setVisible(true);

        indicator3.setBounds(5, 35, 80, 30);
        indicator3.setVisible(true);

        nextButton.setBounds(5, 35, 80, 30);
        nextButton.setVisible(true);

        nameField.setBounds(100, 30, 200, 40);



        this.add(nextButton);
        this.add(nameField);
        this.add(indicator1);
        this.add(indicator2);
        this.add(indicator3);

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
