package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;

public class ReplacePanel extends BaseStatePanel {
    private JLabel _selectedTerritoryLabel;
    private JButton _tradeUnitsButton;
    private JButton _nextPhaseButton;
    private JComboBox<Integer> _infantryAmountComboBox;
    private JComboBox<Integer> _cavalryAmountComboBox;

    public ReplacePanel() {
        super("Trade your army units.");

    }

    @Override
    public void initializeComponents() {

    }

    @Override
    public void addComponents() {

    }
}
