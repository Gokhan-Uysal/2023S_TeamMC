package app;

import javax.swing.SwingUtilities;

import app.domain.models.game.GameState;
import app.domain.services.GameManagerService;
import app.ui.views.menu.MainMenuFrame;
import app.ui.views.menu.playermenu.MapBuildFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // new MainMenuFrame();
            new MapBuildFrame();
            GameManagerService.getInstance().setState(GameState.BUILDING_STATE);
            GameManagerService.getInstance().initializeGame();
        });
    }
}