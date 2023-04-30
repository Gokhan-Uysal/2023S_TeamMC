package app.ui.views.game;

import app.domain.services.Map.MapService;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.help.HelpPanel;
import app.ui.views.game.map.MapPanel;

import java.awt.*;

import java.io.File;

public class GameFrame extends BaseJFrame {

    // Map mvc
    private MapPanel _mapPanel;
    private MapService _mapService;
    private MapPanelController _mapController;

    public GameFrame(String title, Dimension size, Point location, MapService mapService) {
        super(title, size, location);
        this._mapService = mapService;
        this.setLayout(new BorderLayout());
        initializeComponents();
        buildComponents();
    }

    @Override
    public void initializeComponents() {
        _mapPanel = new MapPanel();
        _mapPanel.setBackground(Color.lightGray);
        _mapController = new MapPanelController(_mapPanel, _mapService);
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
        this.add(_mapPanel, BorderLayout.CENTER);
    }

    private void showMap() {
        _mapController.drawMap();
    }

}
