package app.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import app.common.errors.DbException;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.entities.AdjacentCountryEntity;
import app.domain.models.entities.CountryArmyEntity;
import app.domain.models.entities.CountryEntity;

public class CountryRepository extends BaseRepository {

    public CountryRepository() {
        super("country");
    }

    public List<CountryEntity> findCountries(int limit, int offset) throws DbException {
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
            resultSet.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return countryEntityList;
    }

    public CountryEntity findCountryById(int id) throws DbException {
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
                break;
            }
            resultSet.close();
            return builder.build();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<AdjacentCountryEntity> findAdjacentCountriesById(int id) throws DbException {
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
            resultSet.close();
            return adjCountryEntityList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public CountryArmyEntity findCountryArmy(int id) throws DbException {
        String query = String.format(
                "SELECT c.id, c.name AS country_name, SUM(CASE WHEN a.name = 'Infantry' THEN ta.count ELSE 0 END) AS infantry_count, SUM(CASE WHEN a.name = 'Cavalry' THEN ta.count ELSE 0 END) AS cavalry_count, SUM(CASE WHEN a.name = 'Artillery' THEN ta.count ELSE 0 END) AS artilary_count FROM country c INNER JOIN territory_army ta ON c.id = ta.country_id INNER JOIN army a ON ta.army_id = a.id WHERE c.id = %d GROUP BY c.id, c.name;",
                id);
        try {
            ResultSet resultSet = super.executeQuery(query);
            CountryArmyEntity.Builder builder = new CountryArmyEntity.Builder();

            while (resultSet.next()) {
                builder.setId(resultSet.getInt(1));
                builder.setCountryName(resultSet.getString(2));
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

    public void updateCountryArmy(int id, int count, ArmyCardType armyUnit) throws DbException {
        String query = String.format(
                "UPDATE territory_army SET count = %d WHERE country_id = %d AND army_id = ( SELECT id FROM army WHERE name = '%s');",
                count, id, armyUnit.toString());
        try {
            super.executeQuery(query);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
