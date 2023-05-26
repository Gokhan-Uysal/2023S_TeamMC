package app.domain.models.entities;

import java.sql.Timestamp;

public class GameStatePersistEntity extends BaseEntity {
    private int game_state;
    private Timestamp last_save;

    private GameStatePersistEntity(int id, int game_state, Timestamp last_save) {
        super(id);
        this.game_state = game_state;
        this.last_save = last_save;
    }

    public int getGameState() {
        return game_state;
    }

    public Timestamp getLastSave() {
        return last_save;
    }

    public static class Builder {
        private int id;
        private int game_state;
        private Timestamp last_save;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setGameState(int game_state) {
            this.game_state = game_state;
            return this;
        }

        public Builder setLastSave(Timestamp last_save) {
            this.last_save = last_save;
            return this;
        }

        public GameStatePersistEntity build() {
            return new GameStatePersistEntity(id, game_state, last_save);
        }
    }
}
