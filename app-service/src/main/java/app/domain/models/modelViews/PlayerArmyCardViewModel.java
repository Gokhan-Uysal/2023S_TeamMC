package app.domain.models.modelViews;

import app.domain.models.entities.BaseEntity;

public class PlayerArmyCardViewModel extends BaseEntity {
    public String username;
    public int infantry_count;
    public int cavalry_count;
    public int artillery_count;

    public PlayerArmyCardViewModel(int id, String username, int infantry_count, int cavalry_count,
            int artillery_count) {
        super(id);
        this.username = username;
        this.infantry_count = infantry_count;
        this.cavalry_count = cavalry_count;
        this.artillery_count = artillery_count;
    }

    public static class Builder {
        private int id;
        private String username;
        private int infantry_count;
        private int cavalry_count;
        private int artillery_count;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setInfantryCount(int infantry_count) {
            this.infantry_count = infantry_count;
            return this;
        }

        public Builder setCavalryCount(int cavalry_count) {
            this.cavalry_count = cavalry_count;
            return this;
        }

        public Builder setArtilleryCount(int artillery_count) {
            this.artillery_count = artillery_count;
            return this;
        }

        public PlayerArmyCardViewModel build() {
            return new PlayerArmyCardViewModel(id, username, infantry_count, cavalry_count, artillery_count);
        }
    }
}
