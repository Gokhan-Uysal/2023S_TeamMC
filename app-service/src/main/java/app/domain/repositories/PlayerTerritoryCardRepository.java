package app.domain.repositories;

import java.sql.SQLException;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.entities.PlayerTerritoryCardEntity;

public class PlayerTerritoryCardRepository extends BaseRepository {

    public PlayerTerritoryCardRepository() {
        super("player_territory_card");
    }

    public void insertPlayerTerritoryCard(PlayerTerritoryCardEntity playerTerritoryCardEntity)
            throws NoSuchFieldException, SecurityException, DbException {
        try {
            super.insertEntity(playerTerritoryCardEntity, false);
        } catch (SQLException e) {
            Logger.warning(e.getMessage());
        }
    }

    public void deletePlayerTerritoryCards() throws DbException {
        try {
            super.deleteAllTable();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
