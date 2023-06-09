package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class ReceivePanel extends BaseStatePanel {

    private JLabel _receivedUnitNumberLabel;
    private JLabel _selectedTerritoryLabel;
    private JButton _placeUnitsButton;

    public ReceivePanel() {
        super("Place your army units to map");
        this.setLayout(new GridLayout(5,1));
        initializeComponents();
        addComponents();
    }

    @Override
    public void initializeComponents() {
        this._receivedUnitNumberLabel = new JLabel("You have received: ");
        this._selectedTerritoryLabel = new JLabel("Please select the territory that you want to place your units.");
        this._placeUnitsButton = new JButton("Place army units.");
        setButtonActive(false);
    }

    @Override
    public void addComponents() {
        this.add(_receivedUnitNumberLabel);
        this.add(_selectedTerritoryLabel);
        this.add(_placeUnitsButton);
    }

    public void setButtonActive(boolean isActive) {
        _placeUnitsButton.setEnabled(!isActive);
    }

    public void updateSelectedTerritory(String territoryName){
        _selectedTerritoryLabel.setText(String.format("The selected territory is %s.\n", territoryName));
        this.refresh();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public JButton getPlaceUnitsButton(){
        return this._placeUnitsButton;
    }

    public void setReceivedUnitNumber(int armyUnitNumber){
        this._receivedUnitNumberLabel.setText(String.format("You have received %d units.", armyUnitNumber));
    }

}
