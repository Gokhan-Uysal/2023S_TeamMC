package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.entities.PlayerTerritoryCardEntity;
import app.domain.models.entities.TerritoryCardEntity;

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

    public List<TerritoryCardEntity> findTerritoryCardsByPlayerId(int id) throws DbException {
        try {
            String query = String.format(
                    "SELECT id, description, image, country_id FROM player_territory_card INNER JOIN territory_card tc on player_territory_card.territory_card_id = tc.id WHERE tc.id=%s",
                    id);
            ResultSet resultSet = executeQuery(query);
            List<TerritoryCardEntity> territoryCardEntities = new ArrayList<>();

            while (resultSet.next()) {
                TerritoryCardEntity.Builder builder = new TerritoryCardEntity.Builder();
                builder.setId(resultSet.getInt(1));
                builder.setDescription(resultSet.getString(2));
                builder.setImage(resultSet.getString(3));
                builder.setCountryId(resultSet.getInt(4));
                territoryCardEntities.add(builder.build());
            }
            resultSet.close();
            return territoryCardEntities;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
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
