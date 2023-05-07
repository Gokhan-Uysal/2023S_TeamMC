package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class CardTradePanel extends BaseStatePanel {

    private JButton _tradeArmyCardButton;
    private JButton _tradeTerritoryCardButton;
    private JButton _nextPhaseButton;
    public JComboBox<Integer> infantryCardBox;
    public JComboBox<Integer> cavalryCardBox;
    public JComboBox<Integer> artilleryCardBox;
    public JComboBox<String> continentListBox;

    public JButton getTradeArmyCardButton() {
        return this._tradeArmyCardButton;
    }

    public JButton getTradeTerritoryCardButton() {
        return this._tradeTerritoryCardButton;
    }

    public JButton getNextPhaseButton() {
        return this._nextPhaseButton;
    }

    public CardTradePanel() {
        super("Please select the card type you want to trade.");
        this.setLayout(new GridBagLayout());
        initializeComponents();
        addComponents();
    }

    public void toggleVisiblty(Component component) {
        component.setEnabled(!component.isVisible());
    }

    public void addItemToCardBox(JComboBox<Integer> comboBox, int item) {
        addItemToBox(comboBox, item);
    }

    public void addItemToTerritoryBox(JComboBox<String> comboBox, String item) {
        addItemToBox(comboBox, item);
    }

    public <Item> void addItemToBox(JComboBox<Item> comboBox, Item item) {
        comboBox.addItem(item);
    }

    @Override
    public void initializeComponents() {
        this._tradeArmyCardButton = new JButton("Trade army cards.");
        this._tradeTerritoryCardButton = new JButton("Trade territory cards.");
        this._nextPhaseButton = new JButton("Next phase ->");

        this.infantryCardBox = new JComboBox<>();
        this.cavalryCardBox = new JComboBox<>();
        this.artilleryCardBox = new JComboBox<>();
        this.continentListBox = new JComboBox<>();
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
        constraint.gridwidth = 1;
        this.add(_tradeArmyCardButton, constraint);

        // New row with Cavalry, Artillery, Infantry
        constraint.gridx = 1;
        constraint.gridy = 1;
        this.add(new JLabel("Infantry"), constraint);

        constraint.gridx = 2;
        this.add(new JLabel("Cavalry"), constraint);

        constraint.gridx = 3;
        this.add(new JLabel("Artillery"), constraint);

        constraint.gridx = 1;
        constraint.gridy = 2;
        this.add(infantryCardBox, constraint);

        constraint.gridx = 2;
        this.add(cavalryCardBox, constraint);

        constraint.gridx = 3;
        this.add(artilleryCardBox, constraint);

        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        this.add(_tradeTerritoryCardButton, constraint);

        constraint.gridx = 1;
        constraint.gridwidth = 2;
        this.add(continentListBox, constraint);

        constraint.gridx = 1;
        constraint.gridy = 4;
        this.add(_nextPhaseButton, constraint);

    }
}
