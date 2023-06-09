package app.ui.views.game.pause;

import app.ui.views.components.BaseJFrame;

import javax.swing.*;
import java.awt.*;

public class PauseScreenFrame extends BaseJFrame {

    private JLabel pauseLabel;

    public PauseScreenFrame(String title, Dimension size) {
        super(title, size);
        this.setLayout(new BorderLayout());
        initializeComponents();
        buildComponents();
    }

    @Override
    public void initializeComponents() {
        pauseLabel = new JLabel("Game Paused");
        pauseLabel.setHorizontalAlignment(JLabel.CENTER);
        pauseLabel.setVerticalAlignment(JLabel.CENTER);

    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(getRootFrame());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponents();

        this.refresh();
    }

    @Override
    public void addComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(pauseLabel, gbc);

    }

    private JFrame getRootFrame() {

        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }
}
