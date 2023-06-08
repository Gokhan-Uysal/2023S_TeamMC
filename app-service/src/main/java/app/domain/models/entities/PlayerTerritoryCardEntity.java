package app.domain.models.entities;

public class PlayerTerritoryCardEntity {
    public int player_id;
    public int territory_card_id;

    public PlayerTerritoryCardEntity(int player_id, int territory_card_id) {
        this.player_id = player_id;
        this.territory_card_id = territory_card_id;
    }

    public static class Builder {
        private int player_id;
        private int territory_card_id;

        public Builder setPlayerId(int player_id) {
            this.player_id = player_id;
            return this;
        }

        public Builder setTerritoryCardId(int territoryCardId) {
            this.territory_card_id = territoryCardId;
            return this;
        }

        public PlayerTerritoryCardEntity build() {
            return new PlayerTerritoryCardEntity(player_id, territory_card_id);
        }
    }

    @Override
    public String toString() {
        return "PlayerArmyCardEntity{" +
                "player_id=" + player_id +
                ", territory_card_id=" + territory_card_id +
                '}';
    }
}
