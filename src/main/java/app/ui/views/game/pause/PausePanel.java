package app.ui.views.game.pause;
import app.ui.controllers.game.pausescreen.PausePanelController;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel{

    public JButton pauseButton;

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
        new PausePanelController(this);
    }

    private void addComponents() {
        this.add(pauseButton);
    }
}
