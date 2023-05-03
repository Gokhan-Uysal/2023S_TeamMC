package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;

import app.ui.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame("Main Menu", AppConfig.appSize);
        });
    }
}