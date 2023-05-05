package app.ui.controllers.menu.playermenu;

import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.ui.controllers.game.map.MapBuildFrameController;
import app.ui.views.menu.playermenu.MapBuildFrame;
import app.ui.views.menu.playermenu.PlayerMenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerMenuController implements ActionListener {

    private PlayerMenuFrame playerMenuFrame;

    public PlayerMenuController(PlayerMenuFrame playerMenuFrame) {

        this.playerMenuFrame = playerMenuFrame;
        playerMenuFrame.playButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerMenuFrame.playButton) {
            ArrayList<String> newNames = new ArrayList<String>();
            for (PlayerPanelController playerPanelController : playerMenuFrame.playerPanelControllers) {
                newNames.addAll(playerPanelController.newNames);
            }
            if (newNames.size() < 2) {
                JOptionPane.showMessageDialog(playerMenuFrame, "Please enter name for at least 2 players", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int a = JOptionPane.showConfirmDialog(playerMenuFrame, "Are you sure? Do you want to start playing?");
            if (a == JOptionPane.YES_OPTION) {
                PlayerService.createPlayer(newNames);
                MapBuildFrame mapBuildFrame = new MapBuildFrame(playerMenuFrame.getLocation());
                new MapBuildFrameController(mapBuildFrame);
                GameManagerService.getInstance().initializeGame();
                playerMenuFrame.dispose();
            }
        }
    }
}
