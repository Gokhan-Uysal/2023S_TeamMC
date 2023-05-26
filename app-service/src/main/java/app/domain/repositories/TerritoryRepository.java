package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import app.common.Logger;
import app.domain.models.entities.CountryEntity;

public class TerritoryRepository extends BaseRepository {

    public TerritoryRepository() {
        super("country");
    }

    public List<CountryEntity> findTerritories(int limit, int offset) {
        List<CountryEntity> countryEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = super.getMany(limit, offset);
            CountryEntity.Builder builder = new CountryEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setName(resultSet.getString(2));
                builder.setPositionX(resultSet.getInt(3));
                builder.setPositionY(resultSet.getInt(4));
                builder.setContinentId(resultSet.getInt(5));
                CountryEntity continentEntity = builder.build();
                countryEntityList.add(continentEntity);
            }
        } catch (SQLException e) {
            Logger.error(e);
        }
        return countryEntityList;
    }
}
