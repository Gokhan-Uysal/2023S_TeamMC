package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import app.common.errors.DbException;
import app.domain.models.entities.AdjacentCountryEntity;
import app.domain.models.entities.CountryEntity;

public class TerritoryRepository extends BaseRepository {

    public TerritoryRepository() {
        super("country");
    }

    public List<CountryEntity> findTerritories(int limit, int offset) throws DbException {
        List<CountryEntity> countryEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = super.getMany(limit, offset);
            CountryEntity.Builder builder = new CountryEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setName(resultSet.getString(2));
                builder.setImageName(resultSet.getString(3));
                builder.setPositionX(resultSet.getInt(4));
                builder.setPositionY(resultSet.getInt(5));
                builder.setContinentId(resultSet.getInt(6));
                CountryEntity continentEntity = builder.build();
                countryEntityList.add(continentEntity);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return countryEntityList;
    }

    public CountryEntity findTerritoryById(int id) throws DbException {
        try {
            ResultSet resultSet = super.getById(id);
            CountryEntity.Builder builder = new CountryEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setName(resultSet.getString(2));
                builder.setImageName(resultSet.getString(3));
                builder.setPositionX(resultSet.getInt(4));
                builder.setPositionY(resultSet.getInt(5));
                builder.setContinentId(resultSet.getInt(6));
            }
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<AdjacentCountryEntity> findAdjacentTerritoriesById(int id) throws DbException {
        List<AdjacentCountryEntity> adjCountryEntityList = new ArrayList<>();
        String query = String.format(
                "SELECT c1.name AS country_name, c2.name AS adjacent_country_name FROM country c1 INNER JOIN adjacent_country ac on c1.id = ac.country_id INNER JOIN country c2 ON ac.adjacent_country_id=c2.id WHERE c1.id=%d;",
                id);
        try {
            ResultSet resultSet = super.executeQuery(query);
            while (resultSet.next()) {
                AdjacentCountryEntity.Builder builder = new AdjacentCountryEntity.Builder();
                builder.setCountryName(resultSet.getString(1));
                builder.setAdjacentCountryName(resultSet.getString(2));
                AdjacentCountryEntity continentEntity = builder.build();
                adjCountryEntityList.add(continentEntity);
            }
            return adjCountryEntityList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }
}
