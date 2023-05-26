package app;

import javax.swing.SwingUtilities;

import app.common.errors.DbException;
import app.domain.models.entities.CountryEntity;
import app.domain.repositories.TerritoryRepository;
import app.ui.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        // // new MainMenuFrame();

        // });

        TerritoryRepository tr = new TerritoryRepository();
    }
}