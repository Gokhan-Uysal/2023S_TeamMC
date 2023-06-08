package app;

import app.common.errors.DbException;
import app.domain.models.player.Player;
import app.domain.repositories.PlayerRepository;
import app.domain.services.DbSaveLoadService;
import app.ui.views.menu.MainMenuFrame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws DbException {

        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame();
        });
    }
}