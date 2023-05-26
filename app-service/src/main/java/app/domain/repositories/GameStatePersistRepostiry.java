package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.common.errors.DbException;
import app.domain.models.entities.GameStatePersistEntity;

public class GameStatePersistRepostiry extends BaseRepository {
    public GameStatePersistRepostiry() {
        super("game_state_persist");
    }

    public GameStatePersistEntity findGameStateBySeasionId(int id) throws DbException {
        try {
            ResultSet resultSet = super.getById(id);
            GameStatePersistEntity.Builder builder = new GameStatePersistEntity.Builder();
            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setGameState(resultSet.getInt(2));
                builder.setLastSave(resultSet.getTimestamp(3));
            }
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<GameStatePersistEntity> findGameStates(int limit, int offset) throws DbException {
        try {
            ResultSet resultSet = super.getMany(limit, offset);
            List<GameStatePersistEntity> seasionList = new ArrayList<>();
            GameStatePersistEntity.Builder builder = new GameStatePersistEntity.Builder();
            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setGameState(resultSet.getInt(2));
                builder.setLastSave(resultSet.getTimestamp(3));
                GameStatePersistEntity gameStatePersistEntity = builder.build();
                seasionList.add(gameStatePersistEntity);
            }
            return seasionList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
