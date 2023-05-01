package app.ui.controllers.game.map.player;

import app.domain.services.GameManagerService;
import app.ui.views.game.player.PlayerStatePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerStatePanelController implements ActionListener {

    PlayerStatePanel playerStatePanel = new PlayerStatePanel();
    GameManagerService gameManagerService = new GameManagerService();

    public PlayerStatePanelController(PlayerStatePanel playerStatePanel){
        this.playerStatePanel = playerStatePanel;
        playerStatePanel.nextButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerStatePanel.nextButton){
            gameManagerService.handleNextButtonClick();
        }
    }
}
