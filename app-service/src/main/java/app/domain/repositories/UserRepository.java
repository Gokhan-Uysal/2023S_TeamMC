package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.common.errors.DbException;
import app.domain.models.entities.UserEntity;

public class UserRepository extends BaseRepository implements IDbAdapter<UserEntity> {

    public UserRepository(String tableName) {
        super(tableName);
    }

    @Override
    public UserEntity getItemById(int id) throws DbException {
        try (ResultSet result = super.getById(id)) {

            if (result == null) {
                throw new DbException("User not found");
            }

            if (result.next()) {
                return new UserEntity(result.getInt("id"), result.getString("username"), result.getInt("high_score"));
            }

            throw new DbException("User not found");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public UserEntity[] getManyItems(int limit, int offset) throws DbException {
        UserEntity[] users = {};
        try {
            ResultSet userData = super.getMany(limit, offset);
            System.out.println(userData);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return users;
    }

    @Override
    public void createItem(UserEntity model) throws DbException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createItem'");
    }

    @Override
    public void deleteItem(int id) throws DbException {
        try {
            super.deleteById(id);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void updateItem(UserEntity model) throws DbException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItem'");
    }
}
