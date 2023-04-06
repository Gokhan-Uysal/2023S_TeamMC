package app.presentation.views.menu;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {

    public ButtonsPanel(int row, int col) {
        buildView(row, col);
    }

    private void buildView(int row, int col) {
        this.setLayout(new GridLayout(row, col, 2, 2));
        this.add(button("Start"));
        this.add(button("Settings"));
        this.add(button("Exit"));
    }

    public JButton button(String title) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(100, 20));
        button.addActionListener(null);
        return button;
    }
}
