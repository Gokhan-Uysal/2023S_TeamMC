package app.presentation.views.game.Map;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.business.services.MapService;
import app.presentation.controllers.game.map.MapController;

public class MapFactory extends JPanel {

    // Map mvc init
    public MapPanel mapPanel;
    public MapService mapService;
    public MapController mapController;

    public MapFactory() {
        buildClass();
        buildView();
    }

    private void buildView() {
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(mapPanel), BorderLayout.CENTER);
    }

    private void buildClass() {
        initMap();
    }

    private void initMap() {
        this.mapPanel = new MapPanel(15);
        this.mapService = new MapService(new File("src/main/java/app/resource/assets/ConKUeror.png"), 55);
        this.mapController = new MapController(mapService, mapPanel);
    }

    public void drawMap() {
        this.mapController.drawMap();
    }
}
