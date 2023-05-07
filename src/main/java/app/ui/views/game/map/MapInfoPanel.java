package app.ui.views.game.map;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.common.AppConfig;

public class MapInfoPanel extends JPanel {
    private JLabel _owner;
    private JLabel _territoryNameLabel;
    private JLabel _infantryCountLabel;
    private JLabel _chivalryCountLabel;
    private JLabel _artilleryCountLabel;

    public MapInfoPanel() {
        buildPanel();
    }

    private void buildPanel() {
        this.setLayout(new GridLayout(5, 1));
        this._owner = getLabel("Owner: N/A");
        this._territoryNameLabel = getLabel("N/A");
        this._infantryCountLabel = getLabel("Infantry: 0");
        this._chivalryCountLabel = getLabel("Chivalry: 0");
        this._artilleryCountLabel = getLabel("Artillery: 0");
        this.add(_owner);
        this.add(_territoryNameLabel);
        this.add(_infantryCountLabel);
        this.add(_chivalryCountLabel);
        this.add(_artilleryCountLabel);
        this.setBackground(Color.ORANGE);
        this.setBounds((int) (AppConfig.appSize.getWidth() - 250), 0, 250, 200);
    }

    public void updateInfo(String owner, String territoryName, int infantryCount, int chivalryCount,
            int artilleryCount) {
        _owner.setText("Owner: " + owner);
        _territoryNameLabel.setText(territoryName);
        _infantryCountLabel.setText("Infantry: " + Integer.toString(infantryCount));
        _chivalryCountLabel.setText("Chivalry: " + Integer.toString(chivalryCount));
        _artilleryCountLabel.setText("Artillery: " + Integer.toString(artilleryCount));
    }

    private JLabel getLabel(String info) {
        return new JLabel(info);
    }
}
