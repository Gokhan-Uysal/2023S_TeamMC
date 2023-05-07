package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class AttackPanel extends BaseStatePanel {

    private JLabel _attackerTerritory;
    private JLabel _defenderTerritory;
    private JButton _attackButton;
    private JButton _endPhaseButton;

    public JButton getAttackButton() {
        return this._attackButton;
    }

    public JButton getEndPhaseButton() {
        return this._endPhaseButton;
    }

    public AttackPanel() {
        super("Please select the attacker and defender territory.");
        this.setLayout(new GridLayout(5, 1));
        initializeComponents();
        addComponents();
    }

    @Override
    public void initializeComponents() {
        this._attackerTerritory = new JLabel("Please select the attacker territory.");
        this._defenderTerritory = new JLabel("Please select the defender territory.");
        this._attackButton = new JButton("ATTACK!");
        setButtonActive(false);
        this._endPhaseButton = new JButton("End Attack Phase!");
    }

    @Override
    public void addComponents() {
        this.add(_attackerTerritory);
        this.add(_defenderTerritory);
        this.add(_attackButton);
        this.add(_endPhaseButton);
    }

    public void setButtonActive(boolean isActive) {
        _attackButton.setEnabled(isActive);
    }

    public void updateAttackerTerritory(String territoryName) {
        _attackerTerritory.setText(String.format("The attacker territory is %s\n", territoryName));
        this.refresh();
    }

    public void updateDefenderTerritory(String territoryName) {
        _defenderTerritory.setText(String.format("The defender territory is %s\n", territoryName));
        this.refresh();
    }

    public void clearLabels() {
        this._attackerTerritory.setText("Please select the attacker territory.");
        this._defenderTerritory.setText("Please select the defender territory.");
        this.refresh();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}
