package app;

import app.common.errors.DbException;
import app.ui.views.menu.MainMenuFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws DbException {
        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame();
        });
    }
}