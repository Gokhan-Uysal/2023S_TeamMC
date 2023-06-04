package app;

import javax.swing.SwingUtilities;

import app.common.errors.DbException;
import app.domain.models.card.army.ArmyCardType;
import app.domain.repositories.PlayerRepository;
import app.ui.views.menu.MainMenuFrame;

public class Main {
    public static void main(String[] args) throws DbException {
        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame();
            PlayerRepository pr = new PlayerRepository();
            try {
                pr.findPlayers(0, 0).forEach(p -> {
                    System.out.println(p);
                });
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}