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
        initializeComponents();
        addComponents();
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
        GridBagConstraints constraint = new GridBagConstraints();

        constraint.anchor = GridBagConstraints.CENTER;
        constraint.weightx = 0.5;
        constraint.weighty = 0.5;

        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 4;
        constraint.gridheight = 1;

        constraint.fill = GridBagConstraints.HORIZONTAL;
        this.add(super._infoLabel, constraint);

        constraint.gridx = 0;
        constraint.gridy = 1;
        this.add(_startTerritoryLabel, constraint);

        constraint.gridy = 2;
        this.add(_destinationTerritoryLabel, constraint);

        constraint.gridy = 3;
        constraint.gridwidth = 1;
        this.add(new JLabel("Infantry"), constraint);

        constraint.gridx = 1;
        this.add(new JLabel("Cavalry"), constraint);

        constraint.gridx = 2;
        this.add(new JLabel("Artillery"), constraint);

        constraint.gridx = 0;
        constraint.gridy = 4;
        this.add(_infantryComboBox, constraint);

        constraint.gridx = 1;
        this.add(_cavalryComboBox, constraint);

        constraint.gridx = 2;
        this.add(_artilleryComboBox, constraint);

        constraint.gridx = 5;
        constraint.gridy = 1;
        constraint.gridwidth = 2;
        this.add(_fortifyButton, constraint);

        constraint.gridy = 2;
        this.add(_nextPhaseButton, constraint);
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
