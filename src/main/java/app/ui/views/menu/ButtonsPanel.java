package app.ui.views.menu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
    public JButton startButton;
    public JButton settingsButton;
    public JButton exitButton;

    public ButtonsPanel(int row, int col) {
        buildView(row, col);
    }

    private void buildView(int row, int col) {
        this.setLayout(new GridLayout(row, col, 2, 2));
        startButton = new JButton("Start");
        settingsButton = new JButton("Settings");
        exitButton = new JButton("Exit");

        this.add(startButton);
        this.add(settingsButton);
        this.add(exitButton);
    }
}
