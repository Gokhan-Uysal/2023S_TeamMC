package app.domain.models.game;

public enum GameState {
    LOADING_STATE(1),
    BUILDING_STATE(2),
    DISTRIBUTING_STATE(3),
    RECEIVING_STATE(4),
    REPLACEMENT_STATE(5),
    TRADE_CARD_STATE(6),
    ATTACK_STATE(7),
    FORTIFY_STATE(8),
    GAME_OVER_STATE(9);

    private final int value;

    GameState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GameState fromInt(int i) {
        for (GameState b : GameState.values()) {
            if (b.getValue() == i) {
                return b;
            }
        }
        return null;
    }
}
