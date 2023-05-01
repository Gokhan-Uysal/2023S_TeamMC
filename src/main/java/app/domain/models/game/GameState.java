package app.domain.models.game;

public enum GameState {
    INITIALIZATION(0),
    REPLACEMENT_PHASE(1),
    ATTACK_PHASE(2),
    FORTIFY_PHASE(3),
    END_TURN(4),
    GAME_OVER(5);

    private int value;

    private GameState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
