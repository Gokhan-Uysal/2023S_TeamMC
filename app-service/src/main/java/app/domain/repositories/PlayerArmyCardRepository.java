package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.entities.PlayerArmyCardEntity;
import app.domain.models.modelViews.PlayerArmyCardViewModel;

public class PlayerArmyCardRepository extends BaseRepository {
    public PlayerArmyCardRepository() {
        super("player_army_card");
    }

    public void insertPlayerArmyCard(PlayerArmyCardEntity playerArmyCardEntity)
            throws NoSuchFieldException, SecurityException {
        try {
            super.insertEntity(playerArmyCardEntity, false);
        } catch (SQLException e) {
            Logger.warning(e.getMessage());
        }
    }

    public PlayerArmyCardViewModel findPlayerArmyCard(int id) throws DbException {
        String query = String.format(
                "SELECT p.id, p.username AS player_username, SUM(CASE WHEN ac.name = 'Infantry' THEN pac.count ELSE 0 END) AS infantry_count, SUM(CASE WHEN ac.name = 'Cavalry' THEN pac.count ELSE 0 END) AS cavalry_count, SUM(CASE WHEN ac.name = 'Artillery' THEN pac.count ELSE 0 END) AS artilary_count FROM player p INNER JOIN player_army_card pac ON p.id = pac.player_id INNER JOIN army_card ac ON pac.army_card_id = ac.id WHERE p.id = %d GROUP BY p.id, p.username;",
                id);
        try {
            ResultSet resultSet = super.executeQuery(query);
            PlayerArmyCardViewModel.Builder builder = new PlayerArmyCardViewModel.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setUsername(resultSet.getString(2));
                builder.setInfantryCount(resultSet.getInt(3));
                builder.setCavalryCount(resultSet.getInt(4));
                builder.setArtilleryCount(resultSet.getInt(5));
                break;
            }
            resultSet.close();
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void updatePlayerArmyCard(int id, int count, ArmyCardType armyCardUnit) {
        String query = String.format(
                "UPDATE player_army_card SET count = %d WHERE player_id = %d AND army_card_id = ( SELECT id FROM army_card WHERE name = '%s');",
                count, id, armyCardUnit.toString());
        try {
            super.executeQuery(query);

        } catch (SQLException e) {
            Logger.warning(e.getMessage());
        }
    }
}
