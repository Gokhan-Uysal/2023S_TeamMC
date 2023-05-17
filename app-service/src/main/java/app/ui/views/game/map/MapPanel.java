package app.ui.views.game.map;

import javax.swing.JPanel;

public class MapPanel extends JPanel {
    public MapInfoPanel mapInfoPanel;

    public MapPanel() {
        buildClass();
        buildView();
    }

    private void buildView() {
        this.setLayout(null);
        this.setVisible(true);
        this.add(mapInfoPanel);
    }

    private void buildClass() {
        mapInfoPanel = new MapInfoPanel();
    }

    public void drawTerriotry(TerritoryComponent trComponent) {
        this.add(trComponent);
        this.refresh();
    }

    public void updateMapInfo(String owner, String territoryName, int infantryCount, int chivalryCount,
            int artilleryCount) {
        mapInfoPanel.updateInfo(owner, territoryName, infantryCount, chivalryCount, artilleryCount);
        this.refresh();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}