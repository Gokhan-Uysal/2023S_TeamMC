package app.domain.services;

import app.domain.models.game.GameState;
import app.domain.services.base.BasePublisher;

public class GameManagerService extends BasePublisher<GameState> {
    private static GameManagerService _instance;
    private GameState gameState;

    private GameManagerService() {
        super(GameState.INITIALIZATION);
        setState(GameState.INITIALIZATION);
        initializeGame();
    }

    public static GameManagerService getInstance() {
        if (_instance == null) {
            _instance = new GameManagerService();
        }
        return _instance;
    }

    private void initializeGame() {
        // Initialize your game here.
        // ...
        // After initialization, set the game state to REPLACEMENT_PHASE.
        setState(GameState.REPLACEMENT_PHASE);
        notifySubscribers(GameState.REPLACEMENT_PHASE);
    }

    public void updateGameState(GameState newState) {
        // Update the game state and perform the corresponding actions.
        setState(newState);
        notifySubscribers(newState);
    }

    // Add methods for handling game logic here.

}