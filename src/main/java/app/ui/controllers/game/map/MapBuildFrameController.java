package app.ui.controllers.game.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.common.Logger;
import app.domain.services.GameManagerService;
import app.ui.views.game.GameFrame;
import app.ui.views.menu.playermenu.MapBuildFrame;

public class MapBuildFrameController implements ActionListener {
    private MapBuildFrame _mapBuildFrame;

    public MapBuildFrameController(MapBuildFrame mapBuildFrame) {
        this._mapBuildFrame = mapBuildFrame;
        _mapBuildFrame.buildGameButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (GameManagerService.getInstance().validateNewBuildMap()) {
            new GameFrame(_mapBuildFrame.getLocation(null));
            GameManagerService.getInstance().handleNextState();
            _mapBuildFrame.dispose();
        } else {
            Logger.warning("Wrong territory adjustemnt");
        }
    }
}