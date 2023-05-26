package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.common.errors.DbException;
import app.domain.models.entities.PlayerEntity;

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

    public void insertPlayer(PlayerEntity playerEntity) throws DbException {
        try {
            super.insertEntity(playerEntity);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
