package app.data_access.repositories;

import app.data_access.connections.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class  BaseRepository {
    private final String tableName;

    protected BaseRepository(String tableName){
        this.tableName = tableName;
    }

    /*
        Database CRUD operations
    */
    protected   <T> ResultSet getItemById(T id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE id=%s", this.tableName, id.toString());
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItems(Map<String, T> searchQuery) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s", this.tableName, mappedQuery);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItems(Map<String, T> searchQuery, int limit, int offset) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s LIMIT %s OFFSET %s", this.tableName, mappedQuery, limit, offset);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItems(int limit, int offset) throws SQLException {
        String query = String.format("SELECT * FROM %s LIMIT %s OFFSET %s", this.tableName, limit, offset);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItems() throws SQLException {
        String query = String.format("SELECT * FROM %s", this.tableName);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success){
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getManyItemsJoined(String referenceTable, String referenceKey, String referenceId) throws SQLException{
        String query = String.format(" SELECT * FROM %s JOIN %s ON %s = %s;", this.tableName, referenceTable, referenceKey, referenceId);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
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
