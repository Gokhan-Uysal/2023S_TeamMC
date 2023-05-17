package app.ui.views.game.help;

import javax.swing.*;

import app.ui.controllers.game.helpscreen.HelpPanelController;

import java.awt.*;

public class HelpPanel extends JPanel {
    public JButton helpButton;

    public HelpPanel() {
        buildView();
        initializeComponents();
        addComponents();
    }

    private void buildView() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    private void initializeComponents() {
        helpButton = new JButton("?");
        new HelpPanelController(this);
    }

    private void addComponents() {
        this.add(helpButton);
    }
}
