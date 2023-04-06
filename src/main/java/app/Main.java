package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.presentation.views.game.GameFrame;
import app.presentation.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // new GameFrame(AppConfig.title, AppConfig.appSize, AppConfig.appLocation);

            new MainMenuFrame("Main Menu", AppConfig.appSize, AppConfig.appLocation);

        });
    }
}