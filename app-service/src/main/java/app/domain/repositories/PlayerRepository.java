package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.entities.PlayerEntity;
import app.domain.models.modelViews.PlayerArmyCardViewModel;

public class PlayerRepository extends BaseRepository {

    public PlayerRepository() {
        super("player");
    }

    public List<PlayerEntity> findPlayers(int limit, int offset) throws DbException {
        List<PlayerEntity> userEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = super.getMany(limit, offset);
            PlayerEntity.Builder builder = new PlayerEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setUsername(resultSet.getString(2));
                builder.setHighScore(resultSet.getInt(3));
                PlayerEntity continentEntity = builder.build();
                userEntityList.add(continentEntity);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return userEntityList;
    }

    public PlayerEntity findPlayerById(int id) throws DbException {
        try {
            ResultSet resultSet = super.getById(id);
            PlayerEntity.Builder builder = new PlayerEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setUsername(resultSet.getString(2));
                builder.setHighScore(resultSet.getInt(3));
            }
            resultSet.close();
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public PlayerArmyCardViewModel findPlayerArmy(int id) throws DbException {
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

    public int insertPlayer(PlayerEntity playerEntity) throws DbException, NoSuchFieldException, SecurityException {
        try {
            ResultSet resultSet = super.insertEntity(playerEntity, true);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void updatePlayerArmyCard(int id, int count, ArmyCardType armyCardUnit) throws DbException {
        String query = String.format(
                "UPDATE player_army_card SET count = %d WHERE player_id = %d AND army_card_id = ( SELECT id FROM army_card WHERE name = '%s');",
                count, id, armyCardUnit.toString());
        try {
            super.executeQuery(query);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void deletePlayer(int id) throws DbException {
        try {
            super.deleteById(id);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
