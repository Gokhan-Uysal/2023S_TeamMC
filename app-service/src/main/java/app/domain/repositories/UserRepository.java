package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.common.errors.DbException;
import app.domain.models.entities.UserEntity;

public class UserRepository extends BaseRepository {

    public UserRepository(String tableName) {
        super(tableName);
    }

    public List<UserEntity> findUsers(int limit, int offset) throws DbException {
        List<UserEntity> userEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = super.getMany(limit, offset);
            UserEntity.Builder builder = new UserEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setUsername(resultSet.getString(2));
                builder.setHighScore(resultSet.getInt(3));
                UserEntity continentEntity = builder.build();
                userEntityList.add(continentEntity);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return userEntityList;
    }

    public UserEntity findUserById(int id) throws DbException {
        try {
            ResultSet resultSet = super.getById(id);
            UserEntity.Builder builder = new UserEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setUsername(resultSet.getString(2));
                builder.setHighScore(resultSet.getInt(3));
            }
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
