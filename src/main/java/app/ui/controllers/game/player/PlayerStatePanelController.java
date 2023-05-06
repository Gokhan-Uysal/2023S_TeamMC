package app.ui.controllers.game.player;

import app.domain.models.game.GameState;
import app.domain.services.GameManagerService;
import app.domain.services.base.ISubscriber;
import app.ui.views.game.player.PlayerStatePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerStatePanelController implements ActionListener, ISubscriber<GameState> {
    PlayerStatePanel playerStatePanel = new PlayerStatePanel();

    public PlayerStatePanelController(PlayerStatePanel playerStatePanel) {
        this.playerStatePanel = playerStatePanel;
        playerStatePanel.nextButton.addActionListener(this);
        GameManagerService.getInstance().addSubscriber(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerStatePanel.nextButton) {
            GameManagerService.getInstance().handleNextState();
        }
    }

    @Override
    public void update(GameState message) {
        playerStatePanel.updateGameStateLabel(message.toString());
    }
}
