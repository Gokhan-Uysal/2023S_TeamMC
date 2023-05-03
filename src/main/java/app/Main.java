package app;

import javax.swing.SwingUtilities;

import app.domain.services.GameManagerService;
import app.ui.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManagerService.getInstance().initializeGame();
            new MainMenuFrame();
        });
    }
}