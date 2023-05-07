package app.ui.views.game.state;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import app.ui.views.components.BaseStatePanel;

public class DistributePanel extends BaseStatePanel {
    private JLabel _selectedTerritoryLabel;
    private JButton _confirmPlacementButton;

    public JButton getConfirmButton() {
        return this._confirmPlacementButton;
    }

    public DistributePanel() {
        super("Please select territory to place your unit");
        this.setLayout(new GridLayout(3, 1));
        initializeComponents();
        addComponents();
    }

    public void initializeComponents() {
        _selectedTerritoryLabel = new JLabel("None of the territories selected");
        _confirmPlacementButton = new JButton("Confitm placement");
    }

    public void addComponents() {
        this.add(_infoLabel);
        this.add(_selectedTerritoryLabel);
        this.add(_confirmPlacementButton);
    }

    public void updateSelectedTerritory(String territoryName) {
        _selectedTerritoryLabel.setText(String.format("Selected territory: %s", territoryName));
    }
}
