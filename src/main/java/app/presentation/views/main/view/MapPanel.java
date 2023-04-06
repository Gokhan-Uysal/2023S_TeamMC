package app.presentation.views.main.view;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.business.services.MapService;
import app.presentation.controllers.main.map.MapController;
import app.presentation.views.main.view.Map.MapView;

public class MapPanel extends JPanel {

    // Map mvc init
    public MapView mapView;
    public MapService mapService;
    public MapController mapController;

    public MapPanel() {
        buildClass();
        buildView();
    }

    private void buildView() {
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(mapView), BorderLayout.CENTER);
    }

    private void buildClass() {
        initMap();
    }

    private void initMap() {
        this.mapView = new MapView(15);
        this.mapService = new MapService(new File("src/main/java/app/resource/assets/ConKUeror.png"), 55);
        this.mapController = new MapController(mapService, mapView);
    }

    public void drawMap() {
        this.mapController.drawMap();
    }
}
