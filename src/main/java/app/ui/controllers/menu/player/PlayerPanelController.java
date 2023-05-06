package app.ui.controllers.menu.player;

import app.ui.views.menu.playermenu.PlayerPanel;
import app.util.ActionListenerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanelController implements ActionListener {

    private PlayerPanel playerPanel;
    public ArrayList<String> newNames = new ArrayList<String>();

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
