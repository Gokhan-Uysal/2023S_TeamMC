package app.ui.controllers.playermenu;

import app.common.AppConfig;
import app.domain.models.Player.Player;
import app.domain.services.PlayerService;
import app.ui.views.game.GameFrame;
import app.ui.views.menu.ButtonsPanel;
import app.ui.views.playermenu.PlayerPanel;
import app.util.ActionListenerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanelController implements ActionListener {

    private PlayerPanel playerPanel;
    public ArrayList<String> newNames = new ArrayList<String>();
    PlayerService playerService = new PlayerService();

    public PlayerPanelController(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
        ActionListenerUtil.addActionListener(playerPanel.submitButton, this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerPanel.submitButton) {
            String name = playerPanel.nameField.getText();
            newNames.add(name);
            playerPanel.nameField.setText("Confirmed");
            playerPanel.submitButton.setEnabled(false);

        }
    }
}
