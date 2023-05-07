package app.ui.views.game.state;

import java.awt.GridLayout;

import javax.swing.JLabel;

import app.ui.views.components.BaseStatePanel;

public class DistributePanel extends BaseStatePanel {
    private JLabel _selectedTerritory;

    public DistributePanel() {
        super("Please select territory to place your unit");
        this.setLayout(new GridLayout(3, 1));
        initializeComponents();
        addComponents();
    }

    public void initializeComponents() {
        _selectedTerritory = new JLabel("None of the territories selected");
    }

    public void addComponents() {
        this.add(_infoLabel);
        this.add(_selectedTerritory);
    }

    public void updateSelectedTerritory(String territoryName) {
        _selectedTerritory.setText(String.format("Selected territory: %s", territoryName));
    }
}
