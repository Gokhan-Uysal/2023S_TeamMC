package app;

import javax.swing.SwingUtilities;

import app.domain.models.game.GameState;
import app.domain.services.GameManagerService;
import app.ui.controllers.game.map.MapBuildFrameController;
import app.ui.views.menu.MainMenuFrame;
import app.ui.views.menu.playermenu.MapBuildFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // new MainMenuFrame();
            MapBuildFrame mapBuildFrame = new MapBuildFrame();
            new MapBuildFrameController(mapBuildFrame);
            GameManagerService.getInstance().setState(GameState.BUILDING_STATE);
            GameManagerService.getInstance().initializeGame();
        });
    }
}