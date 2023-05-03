package app.ui.controllers.game.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domain.services.Map.MapService;
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
        _mapService.isValidBuildSelection();
    }
}