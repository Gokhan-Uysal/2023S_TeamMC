package app.ui.views.game;

import app.domain.services.GameManagerService;
import app.domain.services.Map.MapService;
import app.domain.services.TestSubscriver;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.controllers.game.map.player.PlayerStatePanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.map.MapPanel;
import app.ui.views.game.player.PlayerStatePanel;

import java.awt.*;

public class GameFrame extends BaseJFrame {

    // Map mvc
    private MapPanel _mapPanel;
    private MapService _mapService;
    private MapPanelController _mapController;
    private PlayerStatePanel _playerStatePanel;
    private PlayerStatePanelController _playerStatePanelController;
    private TestSubscriver testSubscriver;

    public GameFrame(String title, Dimension size, Point location, MapService mapService) {
        super(title, size, location);
        this._mapService = mapService;
        this.setLayout(new BorderLayout());
        initilizeComponents();
        buildComponents();
    }

    @Override
    public void initilizeComponents() {
        _mapPanel = new MapPanel();
        _mapPanel.setBackground(Color.lightGray);
        _mapController = new MapPanelController(_mapPanel, _mapService);
        _playerStatePanel = new PlayerStatePanel();
        _playerStatePanelController = new PlayerStatePanelController(_playerStatePanel);
        testSubscriver = new TestSubscriver();
        GameManagerService.getInstance().addSubscriber(testSubscriver);
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
        this.add(_playerStatePanel, BorderLayout.SOUTH);
    }

    private void showMap() {
        _mapController.drawMap();
    }

}
