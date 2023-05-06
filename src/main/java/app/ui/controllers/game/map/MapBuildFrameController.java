package app.ui.controllers.game.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domain.services.GameManagerService;
import app.domain.services.map.MapService;
import app.ui.views.game.GameFrame;
import app.ui.views.menu.playermenu.MapBuildFrame;

public class MapBuildFrameController implements ActionListener {
    private MapBuildFrame _mapBuildFrame;
    private MapService _mapService;

    public MapBuildFrameController(MapBuildFrame mapBuildFrame) {
        this._mapBuildFrame = mapBuildFrame;
        this._mapService = new MapService();
        _mapBuildFrame.buildGameButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (_mapService.isValidBuildSelection()) {
            _mapService.removeClosedTerritories();
            MapService mapService = new MapService();
            new GameFrame(_mapBuildFrame.getLocation(), mapService);
            GameManagerService.getInstance().handleNextState();
            _mapBuildFrame.dispose();
        } else {
            System.out.println("Wrong territory adjustemnt");
        }
    }
}