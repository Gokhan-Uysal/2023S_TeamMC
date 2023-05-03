package app.ui.views.menu.playermenu;

import java.awt.*;

import app.common.AppConfig;
import app.domain.services.Map.MapService;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.map.MapPanel;

public class MapBuildFrame extends BaseJFrame {
    private MapPanel _mapPanel;
    private MapPanelController _mapPanelController;
    private MapService _mapService;

    public MapBuildFrame() {
        super("Map Building", AppConfig.appSize);
        initilizeComponents();
        buildComponents();
        addComponents();
    }

    @Override
    public void initilizeComponents() {
        _mapPanel = new MapPanel();
        _mapService = new MapService();
        _mapPanelController = new MapPanelController(_mapPanel, _mapService);
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
        _mapPanelController.drawMap();
    }
}
