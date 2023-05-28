package app.domain.models.entities;

import app.domain.models.card.army.ArmyCardType;

public class PlayerArmyCardEntity {
    public int player_id;
    public int count;
    public int army_card_id;

    public PlayerArmyCardEntity(int player_id, int count, int army_card_id) {
        this.player_id = player_id;
        this.count = count;
        this.army_card_id = army_card_id;
    }

    public static class Builder {
        private int player_id;
        private int count;
        private int army_card_id;

        public Builder setPlayerId(int player_id) {
            this.player_id = player_id;
            return this;
        }

        public Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public void setArmyCardId(ArmyCardType armyCardType) {
            this.army_card_id = armyCardType.ordinal() + 1;
        }

        public PlayerArmyCardEntity build() {
            return new PlayerArmyCardEntity(player_id, count, army_card_id);
        }
    }

    @Override
    public String toString() {
        return "PlayerArmyCardEntity{" +
                "player_id=" + player_id +
                ", count=" + count +
                ", army_card_id=" + army_card_id +
                '}';
    }
}
