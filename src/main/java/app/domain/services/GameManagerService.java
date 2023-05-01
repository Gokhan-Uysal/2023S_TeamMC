package app.domain.services;

import app.domain.models.game.GameState;
import app.domain.services.base.BasePublisher;

public class GameManagerService extends BasePublisher<GameState> {
    private static GameManagerService _instance;
    private GameState gameState;

    public GameManagerService() {
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
    public void handleNextButtonClick() {
        switch (gameState) {
            case REPLACEMENT_PHASE:
                startAttackPhase();
                break;
            case ATTACK_PHASE:
                startFortifyPhase();
                break;
            case FORTIFY_PHASE:
                endTurn();
                startReplacementPhase();
                break;
            default:
                break;
        }
    }

    // ...

    public void startReplacementPhase() {
        // Perform actions related to the replacement phase here.
        // ...
        // After completing the actions, update the game state.
        updateGameState(GameState.REPLACEMENT_PHASE);
    }

    public void startAttackPhase() {
        // Perform actions related to the attack phase here.
        // ...
        // After completing the actions, update the game state.
        updateGameState(GameState.ATTACK_PHASE);
    }

    public void startFortifyPhase() {
        // Perform actions related to the fortify phase here.
        // ...
        // After completing the actions, update the game state.
        updateGameState(GameState.FORTIFY_PHASE);
    }

    public void endTurn() {
        // Perform actions related to the end of the turn here.
        // ...
        // After completing the actions, update the game state.
        updateGameState(GameState.END_TURN);
    }

    // ...

}