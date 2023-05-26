package app;

import javax.swing.SwingUtilities;

import app.common.errors.DbException;
import app.ui.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) throws DbException {
        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame();
        });
    }
}