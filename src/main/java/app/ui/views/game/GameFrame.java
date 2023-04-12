package app.ui.views.game;

import app.domain.services.MapService;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.map.MapPanel;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

public class GameFrame extends BaseJFrame {

    // Map mvc
    private MapPanel mapPanel;
    private MapService mapService;
    private MapPanelController mapController;

    public GameFrame(String title, Dimension size, Point location) {
        super(title, size, location);
        this.setLayout(new BorderLayout());
        initilizeComponents();
        buildComponents();
    }

    @Override
    public void initilizeComponents() {
        mapPanel = new MapPanel(15);
        mapService = new MapService(new File("src/main/java/app/resource/assets/ConKUeror.png"), 55);
        mapController = new MapPanelController(mapService, mapPanel);
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setVisible(true);

        addComponents();
        showMap();

        this.refresh();
    }

    @Override
    public void addComponents() {
        this.add(mapPanel, BorderLayout.CENTER);
    }

    private void showMap() {
        mapController.drawMap();
    }

}
