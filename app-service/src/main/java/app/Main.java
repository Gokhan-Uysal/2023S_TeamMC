package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.common.errors.DbException;
import app.domain.repositories.BaseRepository;
import app.domain.repositories.UserRepository;
import app.ui.controllers.menu.login.LoginFrameController;
import app.ui.views.login.LoginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(AppConfig.appSize);
            new LoginFrameController(loginFrame);
            // UserRepository up = new UserRepository("user");
            // try {
            // up.getItemById(1);
            // } catch (DbException e) {
            // System.out.println(e.getMessage());
            // }
        });
    }
}