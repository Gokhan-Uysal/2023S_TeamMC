package app.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.common.Logger;

public class DatabaseConnection {
    private Connection connection;

    protected Connection getConnection() {
        return connection;
    }

    protected void loadDriver(String driver) throws RuntimeException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Failed to load %s driver", driver), e);
        }
    }

    protected DatabaseConnection(String connectionUrl) {
        loadDriver("org.postgresql.Driver");
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Logger.info("Database connected");
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
}
