package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class FortifyPanel extends BaseStatePanel {
    private JLabel _startTerritoryLabel;
    private JLabel _destinationTerritoryLabel;
    private JButton _fortifyButton;
    private JButton _nextPhaseButton;
    private JComboBox<Integer> _infantryComboBox;
    private JComboBox<Integer> _cavalryComboBox;
    private JComboBox<Integer> _artilleryComboBox;

    public JButton getFortifyButton(){
        return this._fortifyButton;
    }

    public JButton getNextPhaseButton(){
        return this._nextPhaseButton;
    }

    public JComboBox<Integer> getInfantryComboBox(){
        return this._infantryComboBox;
    }

    public JComboBox<Integer> getCavalryComboBox(){
        return this._cavalryComboBox;
    }

    public JComboBox<Integer> getArtilleryComboBox(){
        return this._artilleryComboBox;
    }

    public void addItemToArmyBox(JComboBox<Integer> armyBox, Integer number){
        armyBox.addItem(number);
    }

    public FortifyPanel() {
        super("Choose the territories to be fortified.");
        this.setLayout(new GridBagLayout());
    }

    @Override
    public void initializeComponents() {
        this._startTerritoryLabel = new JLabel("Choose the starting territory.");
        this._destinationTerritoryLabel = new JLabel("Choose the destination territory");

        this._fortifyButton = new JButton("FORTIFY");
        this._nextPhaseButton = new JButton("Go to next phase.");

        this._infantryComboBox = new JComboBox<>();
        this._cavalryComboBox = new JComboBox<>();
        this._artilleryComboBox = new JComboBox<>();
    }

    @Override
    public void addComponents() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addComponents'");
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public void setButtonActivate(boolean isActive){
        _fortifyButton.setEnabled(isActive);
    }

    public void updateStartTerritory(String territory){
        this._startTerritoryLabel.setText(String.format("The start territory is: %s.", territory));
    }

    public void updateDestinationTerritory(String territory){
        this._destinationTerritoryLabel.setText(String.format("The destination territory is: %s.", territory));
    }

    public void clearLabels(){
        this._startTerritoryLabel.setText("Choose the starting territory.");
        this._destinationTerritoryLabel.setText("Choose the destination territory.");
        this.refresh();
    }

}
