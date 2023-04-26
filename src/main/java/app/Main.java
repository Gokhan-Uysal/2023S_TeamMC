package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.ui.controllers.login.LoginFrameController;
import app.ui.views.login.LoginFrame;
import app.ui.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //
            LoginFrame loginFrame = new LoginFrame("Login", AppConfig.appSize);
            LoginFrameController loginFrameController = new LoginFrameController(loginFrame);
        });
    }
}