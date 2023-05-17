package app.domain.repositories;

import app.connections.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public abstract class BaseRepository {
    private final String tableName;

    public BaseRepository(String tableName) {
        this.tableName = tableName;
    }

    /*
     * Database CRUD operations
     */
    protected ResultSet getById(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE id=%d", this.tableName, id);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success) {
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getMany(Map<String, T> searchQuery) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s", this.tableName, mappedQuery);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success) {
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getMany(Map<String, T> searchQuery, int limit, int offset) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s LIMIT %s OFFSET %s", this.tableName, mappedQuery, limit,
                offset);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success) {
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    protected <T> ResultSet getMany(int limit, int offset) throws SQLException {
        String query = String.format("SELECT * FROM %s LIMIT %s OFFSET %s", this.tableName, limit, offset);
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        boolean success = statement.execute(query);
        if (success) {
            return statement.getResultSet();
        }

        throw new SQLException("Query execution failed");
    }

    private <T> String mapQuery(Map<String, T> query) {
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
