package app.domain.repositories;

import java.sql.SQLException;

import app.common.errors.DbException;
import app.domain.models.entities.PlayerArmyCardEntity;

public class PlayerArmyCardRepository extends BaseRepository {
    public PlayerArmyCardRepository() {
        super("player_army_card");
    }

    public void insertPlayerArmyCard(PlayerArmyCardEntity playerArmyCardEntity)
            throws NoSuchFieldException, SecurityException, DbException {
        try {
            super.insertEntity(playerArmyCardEntity, false);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
