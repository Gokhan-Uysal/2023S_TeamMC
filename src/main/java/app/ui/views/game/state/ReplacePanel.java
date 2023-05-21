package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class ReplacePanel extends BaseStatePanel {
    private JLabel _selectedTerritoryLabel;
    private JButton _tradeUnitsButton;
    private JButton _nextPhaseButton;
    private JComboBox<Integer> _infantryAmountComboBox;
    private JComboBox<Integer> _cavalryAmountComboBox;

    public ReplacePanel() {
        super("Trade your army units.");
        this.setLayout(new GridBagLayout());
        initializeComponents();
        addComponents();
    }

    public JButton getTradeUnitsButton(){
        return this._tradeUnitsButton;
    }

    public JButton getNextPhaseButton(){
        return this._nextPhaseButton;
    }

    public JComboBox<Integer> getInfantryAmountComboBox(){
        return this._infantryAmountComboBox;
    }

    public JComboBox<Integer> getCavalryAmountComboBox(){
        return this._cavalryAmountComboBox;
    }

    public void updateTerritoryLabel(String territoryName){
        this._selectedTerritoryLabel.setText(String.format("Selected territory: %s.", territoryName));
        this.refresh();
    }

    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    public void addItemToComboBox(JComboBox<Integer> comboBox, int number){
        comboBox.addItem(number);
    }

    @Override
    public void initializeComponents() {
        this._selectedTerritoryLabel = new JLabel("Please select the territory you want to trade units on.");
        this._tradeUnitsButton = new JButton("Trade units.");
        this._nextPhaseButton = new JButton("Next phase.");

        this._infantryAmountComboBox = new JComboBox<>();
        this._cavalryAmountComboBox = new JComboBox<>();
    }

    @Override
    public void addComponents() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(super._infoLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        this.add(_selectedTerritoryLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(new JLabel("Infantry"), constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        this.add(new JLabel("Cavalry"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        this.add(_infantryAmountComboBox, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        this.add(_cavalryAmountComboBox, constraints);

        constraints.gridx = 4;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        this.add(_tradeUnitsButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        this.add(_nextPhaseButton, constraints);

    }
}
