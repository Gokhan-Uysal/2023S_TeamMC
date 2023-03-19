package app.data_access.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class  BaseRepository {
    private final Connection connection;
    private final String tableName;

    protected BaseRepository(Connection connection, String tableName){
        this.connection = connection;
        this.tableName = tableName;
    }

    /*
        Database CRUD operations
    */
    protected   <T> ResultSet getItemById(T id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE id=%s", this.tableName, id.toString());
        Statement statement = this.connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItems(Map<String, T> searchQuery) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s", this.tableName, mappedQuery);
        Statement statement = this.connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItems(Map<String, T> searchQuery, int limit, int skip) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s LIMIT %s SKIP %s", this.tableName, mappedQuery, limit, skip);
        Statement statement = this.connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    private <T> String mapQuery(Map<String, T> query){
        StringBuilder stringBuilder = new StringBuilder();
        query.forEach((key, value) -> {
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(value);
            stringBuilder.append(" ");
        });

        return stringBuilder.toString().trim();
    }

}
