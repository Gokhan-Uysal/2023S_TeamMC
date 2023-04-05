package app;


import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.presentation.views.GameFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame(AppConfig.title, AppConfig.appSize, AppConfig.color1);
        });

    }
}