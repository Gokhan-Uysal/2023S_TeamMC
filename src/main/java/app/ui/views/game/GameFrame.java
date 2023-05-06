package app.ui.views.game;

import app.common.AppConfig;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.map.MapPanel;

import java.awt.*;

public class GameFrame extends BaseJFrame {

    // Map mvc
    private MapPanel _mapPanel;
    private MapPanelController _mapPanelController;

    private Header _header;

    private Footer _footer;

    public GameFrame(Point location) {
        super("Risk Game", AppConfig.appSize, location);
        this.setLayout(new BorderLayout());
        initializeComponents();
        buildComponents();
    }

    @Override
    public void initializeComponents() {
        _mapPanel = new MapPanel();
        _mapPanelController = new MapPanelController(_mapPanel);

        _footer = new Footer();

        _header = new Header();
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setVisible(true);

        addComponents();
        displayMap();

        this.refresh();
    }

    @Override
    public void addComponents() {
        this.add(_mapPanel, BorderLayout.CENTER);
        this.add(_header, BorderLayout.NORTH);
        this.add(_footer, BorderLayout.SOUTH);
    }

    private void displayMap() {
        _mapPanelController.displayMap();
    }

}
