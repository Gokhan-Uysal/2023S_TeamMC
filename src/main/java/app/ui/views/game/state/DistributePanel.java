package app.ui.views.game.state;

import javax.swing.JLabel;

import app.ui.views.components.BaseStatePanel;

public class DistributePanel extends BaseStatePanel {
    private JLabel _infoLabel;

    public DistributePanel() {
        initializeComponents();
        addComponents();
    }

    public void initializeComponents() {
        _infoLabel = new JLabel("Please select territory to place your unit");
    }

    public void addComponents() {
        this.add(_infoLabel);
    }
}
