package app.domain.models.entities;

public class UserEntity extends BaseEntity {
    public String username;
    public int highScore;

    public UserEntity(int id, String username, int highScore) {
        super(id);
        this.username = username;
        this.highScore = highScore;
    }

    public static class Builder {
        private int id;
        private String username;
        private int highScore;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setHighScore(int highScore) {
            this.highScore = highScore;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, username, highScore);
        }
    }
}
