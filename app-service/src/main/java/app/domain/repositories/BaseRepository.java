package app.domain.repositories;

import app.common.Logger;
import app.connections.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository {
    private final String tableName;

    public BaseRepository(String tableName) {
        this.tableName = tableName;
    }

    protected ResultSet executeQuery(String query) throws SQLException {
        Connection connection = ConnectionPool.getValidConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        if (statement.getResultSet() == null) {
            Logger.warning("No result set returned");
            return null;
        }
        return statement.getResultSet();
    }

    /*
     * Database CRUD operations
     */
    protected ResultSet getById(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE id=%d", this.tableName, id);
        return executeQuery(query);
    }

    protected <T> ResultSet getMany(Map<String, T> searchQuery) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s", this.tableName, mappedQuery);
        return executeQuery(query);
    }

    protected <T> ResultSet getMany(Map<String, T> searchQuery, int limit, int offset) throws SQLException {
        String mappedQuery = mapQuery(searchQuery);
        String query = String.format("SELECT * FROM %s WHERE %s LIMIT %s OFFSET %s", this.tableName, mappedQuery, limit,
                offset);
        return executeQuery(query);
    }

    protected ResultSet getMany(int limit, int offset) throws SQLException {
        String query = String.format("SELECT * FROM %s LIMIT %s OFFSET %s", this.tableName, limit, offset);
        return executeQuery(query);
    }

    protected <T> ResultSet insertEntity(T entity, boolean returnId)
            throws SQLException, NoSuchFieldException, SecurityException {
        Field[] fields = entity.getClass().getDeclaredFields();
        String query = String.format("INSERT INTO %s ", this.tableName);

        List<String> keys = new ArrayList<>(fields.length);
        List<String> values = new ArrayList<>(fields.length);

        for (Field field : fields) {
            try {
                if (field.getName().equals("id")) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(entity);
                keys.add(field.getName());
                values.add("\'" + value.toString() + "\'");
            } catch (IllegalAccessException e) {
                Logger.error(e);
            }
        }

        query += String.format("(%s) VALUES (%s)", String.join(", ", keys), String.join(", ", values));

        if (returnId) {
            query += " RETURNING id";
        }
        return executeQuery(query);
    }

    protected ResultSet deleteById(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE id=%d", tableName, id);
        return executeQuery(query);
    }

    protected ResultSet deleteAllTable() throws SQLException {
        String query = String.format("DELETE FROM %s", tableName);
        return executeQuery(query);
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
