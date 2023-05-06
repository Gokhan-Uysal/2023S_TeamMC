package app.ui.controllers.game.player;

import app.domain.models.game.GameState;
import app.domain.models.player.Player;
import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.domain.services.base.ISubscriber;
import app.ui.views.game.player.TurnStatePanel;

public class TurnStatePanelController implements ISubscriber<GameState> {
    private TurnStatePanel _turnStatePanel;

    public TurnStatePanelController(TurnStatePanel turnStatePanel) {
        this._turnStatePanel = turnStatePanel;
        GameManagerService.getInstance().addSubscriber(this);
    }

    @Override
    public void update(GameState message) {
        if (message == GameState.RECEIVING_STATE) {
            Player currentPlayer = PlayerService.getInstance().getCurrentPlayer();
            _turnStatePanel.updateView(currentPlayer.get_username());
        }
    }
}
