package app.domain.services;

import app.common.Logger;
import app.domain.models.game.GameState;
import app.domain.services.base.ISubscriber;

public class TestSubscriver implements ISubscriber<GameState> {

    @Override
    public void update(GameState message) {
        Logger.log(String.format("New state is: %s", message));
    }

}
