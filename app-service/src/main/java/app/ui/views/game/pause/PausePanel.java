package app.ui.views.game.pause;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {

    public JButton pauseButton;
    public JButton saveButton;
    public JButton loadButton;

    public PausePanel() {
        buildView();
        initializeComponents();
        addComponents();
    }

    private void buildView() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    private void initializeComponents() {
        pauseButton = new JButton("Pause");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
    }

    private void addComponents() {
        this.add(pauseButton);
        this.add(saveButton);
        this.add(loadButton);
    }
}
