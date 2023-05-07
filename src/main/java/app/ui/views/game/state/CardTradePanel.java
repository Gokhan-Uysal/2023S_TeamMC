package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class CardTradePanel extends BaseStatePanel {

    private JButton _tradeArmyCards;
    private JButton _tradeTerritoryCards;
    private JButton _trade;
    private JComboBox<Integer> _infantryCardCount;
    private JComboBox<Integer> _cavalryCardCount;
    private JComboBox<Integer> _artilleryCardCount;
    private JComboBox<String> _continentList;

    public CardTradePanel() {
        super("Please select the card type you want to trade.");
        this.setLayout(new GridLayout(5,3));
        initializeComponents();
        addComponents();
    }

    @Override
    public void initializeComponents() {
        this._tradeArmyCards = new JButton("Trade army cards.");
        this._tradeTerritoryCards = new JButton("Trade army cards.");
        this._trade = new JButton("Trade.");

        int[] cardAmount = new int[]{1,2,3,4,5,6,7,8,9,10};
        this._infantryCardCount = new JComboBox<>();
        this._cavalryCardCount = new JComboBox<>();
        this._artilleryCardCount = new JComboBox<>();
        for (Integer i: cardAmount){
            _infantryCardCount.addItem(i);
            _cavalryCardCount.addItem(i);
            _artilleryCardCount.addItem(i);
        }

        String[] continents = new String[]{"Australia", "South America", "North America", "Asia", "Africa", "Europe"};
        this._continentList = new JComboBox<>();
        for (String s: continents){
            _continentList.addItem(s);
        }
    }

    @Override
    public void addComponents() {

    }
}
