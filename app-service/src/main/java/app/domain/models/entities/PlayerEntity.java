package app.domain.models.entities;

public class PlayerEntity extends BaseEntity {
    public String username;
    public int high_score;

    public PlayerEntity(int id, String username, int highScore) {
        super(id);
        this.username = username;
        this.high_score = highScore;
    }

    public static class Builder {
        private int id;
        private String username;
        private int high_score;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setHighScore(int high_score) {
            this.high_score = high_score;
            return this;
        }

        public PlayerEntity build() {
            return new PlayerEntity(id, username, high_score);
        }
    }
}
