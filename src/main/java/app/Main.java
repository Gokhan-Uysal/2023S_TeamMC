package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.ui.controllers.menu.login.LoginFrameController;
import app.ui.views.login.LoginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(AppConfig.appSize);
            new LoginFrameController(loginFrame);
        });
    }
}