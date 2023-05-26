package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.common.errors.DbException;
import app.domain.models.entities.ContinentEntity;

public class ContinentRepository extends BaseRepository {
    public ContinentRepository() {
        super("continent");
    }

    public List<ContinentEntity> findContinents(int limit, int offset) throws DbException {
        List<ContinentEntity> continentEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = super.getMany(limit, offset);
            ContinentEntity.Builder builder = new ContinentEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setName(resultSet.getString(2));
                ContinentEntity continentEntity = builder.build();
                continentEntityList.add(continentEntity);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return continentEntityList;
    }

    public ContinentEntity findContinentById(int id) throws DbException {
        try {
            ResultSet resultSet = super.getById(id);
            ContinentEntity.Builder builder = new ContinentEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setName(resultSet.getString(2));
            }
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

}
