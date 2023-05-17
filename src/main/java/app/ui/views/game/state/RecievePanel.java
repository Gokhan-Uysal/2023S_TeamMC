package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class RecievePanel extends BaseStatePanel {

    private JLabel _recievedUnitNumberLabel;
    private JLabel _selectedTerritoryLabel;
    private JButton _nextPhaseButton;

    public RecievePanel() {
        super("Place your army units to map");
        this.setLayout(new GridLayout(5,1));
        initializeComponents();
        addComponents();
    }

    @Override
    public void initializeComponents() {
        this._recievedUnitNumberLabel = new JLabel("You have recieved: ");
        this._selectedTerritoryLabel = new JLabel("Please select the territory that you want to place your units.");
        this._nextPhaseButton = new JButton("Go to the trading phase.");
        setButtonActive(false);
    }

    @Override
    public void addComponents() {
        this.add(_recievedUnitNumberLabel);
        this.add(_selectedTerritoryLabel);
        this.add(_nextPhaseButton);
    }

    public void setButtonActive(boolean isActive) {
        _nextPhaseButton.setEnabled(isActive);
    }

}
