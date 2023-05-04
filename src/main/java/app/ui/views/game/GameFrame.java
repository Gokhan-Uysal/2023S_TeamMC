package app.ui.views.game;

import app.common.AppConfig;
import app.domain.services.Map.MapService;
import app.ui.controllers.game.map.MapPanelController;
import app.ui.controllers.game.map.player.PlayerStatePanelController;
import app.ui.controllers.helpscreen.HelpPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.help.HelpPanel;
import app.ui.views.game.map.MapPanel;
import app.ui.views.game.player.PlayerStatePanel;

import java.awt.*;

public class GameFrame extends BaseJFrame {

    // Map mvc
    private MapPanel _mapPanel;
    private MapService _mapService;
    private MapPanelController _mapPanelController;

    private PlayerStatePanel _playerStatePanel;
    private PlayerStatePanelController _playerStatePanelController;

    private HelpPanel _helpPanel;
    private HelpPanelController _helpPanelController;

    public GameFrame(Point location, MapService mapService) {
        super("Risk Game", AppConfig.appSize, location);
        this._mapService = mapService;
        this.setLayout(new BorderLayout());
        initilizeComponents();
        buildComponents();
    }

    @Override
    public void initilizeComponents() {
        _mapPanel = new MapPanel();
        _mapPanelController = new MapPanelController(_mapPanel, _mapService);

        _playerStatePanel = new PlayerStatePanel();
        _playerStatePanelController = new PlayerStatePanelController(_playerStatePanel);

        _helpPanel = new HelpPanel();
        _helpPanelController = new HelpPanelController(_helpPanel);
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
        this.add(_helpPanel, BorderLayout.NORTH);
    }

    private void showMap() {
        _mapPanelController.drawMap();
    }

}
