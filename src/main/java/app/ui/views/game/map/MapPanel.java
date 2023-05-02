package app.ui.views.game.map;

import javax.swing.JPanel;

public class MapPanel extends JPanel {

    public MapPanel() {
        buildClass();
        buildView();
    }

    private void buildView() {
        this.setLayout(null);
        this.setVisible(true);
    }

    private void buildClass() {

    }

    public void drawTerriotry(TerritoryComponent trComponent) {
        this.add(trComponent);
        this.refresh();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}