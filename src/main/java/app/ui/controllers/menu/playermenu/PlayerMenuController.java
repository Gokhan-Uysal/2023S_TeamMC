package app.ui.controllers.menu.playermenu;

import app.common.AppConfig;
import app.domain.services.Map.MapFactory;
import app.domain.services.Map.MapGraphService;
import app.domain.services.Map.MapReadService;
import app.domain.services.Map.MapService;
import app.domain.services.PlayerService;
import app.ui.views.game.GameFrame;
import app.ui.views.menu.playermenu.PlayerMenuFrame;

import javax.swing.*;
import java.awt.*;
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
                Point location = playerMenuFrame.getLocation();

                PlayerService.createPlayer(newNames);

                MapService mapService = new MapService();
                new GameFrame(AppConfig.title, AppConfig.appSize, location, mapService);
                playerMenuFrame.dispose();
            }
        }
    }
}
