package app.data_access.repositories.continent;

import app.data_access.entities.Continent;
import app.data_access.repositories.BaseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ContinentRepository  extends BaseRepository {

    public ContinentRepository(String tableName) {
        super(tableName);
    }

    public Continent getById(int id) throws SQLException {
        ResultSet resultSet = this.getItemById(id);
        Continent.Builder continent = new Continent.Builder();
        while(resultSet.next()){
            String name = resultSet.getString("name");
            continent.name(name);
        }
        resultSet.close();

        return continent.build();
    }

    public <T> ArrayList<Continent> getMany(Map<String, T> query, int limit, int skip) throws SQLException {
        ResultSet resultSet = this.getManyItems(query, limit, skip);
        ArrayList<Continent> continents = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            Continent.Builder continent = new Continent.Builder();
            continent.name(name);

            continents.add(continent.build());
        }
        resultSet.close();

        return continents;
    }

    public <T> ArrayList<Continent> getMany(int limit, int offset) throws SQLException {
        ResultSet resultSet = this.getManyItems(limit, offset);
        ArrayList<Continent> continents = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            Continent.Builder continent = new Continent.Builder();
            continent.name(name);

            continents.add(continent.build());
        }
        resultSet.close();

        return continents;
    }
}
