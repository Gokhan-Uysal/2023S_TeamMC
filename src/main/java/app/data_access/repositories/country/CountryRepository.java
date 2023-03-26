package app.data_access.repositories.country;

import app.data_access.entities.Country;
import app.data_access.repositories.BaseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CountryRepository extends BaseRepository {
    public CountryRepository(String tableName) {
        super(tableName);
    }
    public Country getById(int id) throws SQLException {
        ResultSet resultSet = this.getItemById(id);
        Country.Builder country = new Country.Builder();
        while(resultSet.next()){
            String name = resultSet.getString("name");
            country.name(name);
        }
        resultSet.close();

        return country.build();
    }

    public <T> ArrayList<Country> getMany(Map<String, T> query, int limit, int skip) throws SQLException {
        ResultSet resultSet = this.getManyItems(query, limit, skip);
        ArrayList<Country> countries = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            Country.Builder country = new Country.Builder();
            country.name(name);

            countries.add(country.build());
        }
        resultSet.close();

        return countries;
    }

    public <T> ArrayList<Country> getMany(int limit, int offset) throws SQLException {
        ResultSet resultSet = this.getManyItems(limit, offset);
        ArrayList<Country> countries = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            Country.Builder country = new Country.Builder();
            country.name(name);

            countries.add(country.build());
        }
        resultSet.close();

        return countries;
    }
}
