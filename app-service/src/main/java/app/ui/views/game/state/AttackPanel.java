package app.ui.views.game.state;

import app.ui.views.animations.DiceAnimationPanel;
import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class AttackPanel extends BaseStatePanel {

    private JLabel _attackerTerritory;
    private JLabel _defenderTerritory;
    private JButton _attackButton;
    private JButton _endPhaseButton;
    public DiceAnimationPanel _diceAnimationPanel;

    public JButton getAttackButton() {
        return this._attackButton;
    }

    public JButton getEndPhaseButton() {
        return this._endPhaseButton;
    }

    public AttackPanel() {
        super("Please select the attacker and defender territory.");
        this.setLayout(new GridBagLayout());
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
        this._diceAnimationPanel = new DiceAnimationPanel();
    }

    @Override
    public void addComponents() {
        GridBagConstraints constrain = new GridBagConstraints();
        constrain.anchor = GridBagConstraints.CENTER;
        constrain.weightx = 0.5;
        constrain.weighty = 0.5;

        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.gridwidth = 4;
        constrain.gridheight = 1;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        this.add(super._infoLabel, constrain);

        constrain.gridy = 1;
        this.add(_attackerTerritory, constrain);

        constrain.gridy = 2;
        this.add(_defenderTerritory, constrain);

        constrain.gridy = 3;
        constrain.gridwidth = 1;
        this.add(_attackButton, constrain);

        constrain.gridy = 4;
        this.add(_endPhaseButton, constrain);

        constrain.gridx = 5;
        constrain.gridy = 2;
        constrain.gridwidth = 10;
        constrain.gridheight = 7;
        this.add(_diceAnimationPanel, constrain);
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
