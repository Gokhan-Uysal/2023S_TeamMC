package app.ui.views.menu.playermenu;

import java.awt.*;

import javax.swing.JButton;

import app.common.AppConfig;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.map.MapPanel;

public class MapBuildFrame extends BaseJFrame {
    private MapPanel _mapPanel;
    private MapPanelController _mapPanelController;

    public JButton buildGameButton;

    public MapBuildFrame(Point location) {
        super("Map Building", AppConfig.appSize, location);
        initilizeComponents();
        buildComponents();
        addComponents();
    }

    @Override
    public void initilizeComponents() {
        _mapPanel = new MapPanel();
        _mapPanelController = new MapPanelController(_mapPanel);
        buildGameButton = new JButton("Build Game");
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
        this.add(buildGameButton, BorderLayout.SOUTH);
    }

    private void showMap() {
        _mapPanelController.loadMap();
        _mapPanelController.displayMap();
    }
}
