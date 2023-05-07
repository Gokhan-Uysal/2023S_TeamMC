package app.ui.views.game.state;

import app.ui.views.components.BaseStatePanel;

import javax.swing.*;
import java.awt.*;

public class AttackPanel extends BaseStatePanel {

    private JLabel _attackerTerritory;
    private JLabel _defenderTerritory;

    public AttackPanel() {
        super("Please select the attacker and defender territory.");
        this.setLayout(new GridLayout(3, 1));
        initializeComponents();
        addComponents();
    }


    @Override
    public void initializeComponents() {
        this._attackerTerritory = new JLabel("Please select the attacker territory.");
        this._defenderTerritory = new JLabel("Please select the defender territory.");
    }

    @Override
    public void addComponents() {
        this.add(_attackerTerritory);
        this.add(_defenderTerritory);
    }

    public void updateAttackerTerritory(String territoryName){
        _attackerTerritory.setText(String.format("The attacker territory is ", territoryName));
    }

    public void updateDefenderTerritory(String territoryName){
        _defenderTerritory.setText(String.format("The defender territory is ", territoryName));
    }
}
