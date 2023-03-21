package app.data_access.connections;

import app.common.Layer;
import app.common.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;
    protected Connection getConnection() {
        return connection;
    }

    protected void loadDriver(String driver) throws RuntimeException{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Failed to load %s driver", driver), e);
        }
    }
    protected DatabaseConnection(String connectionUrl){
        loadDriver("org.postgresql.Driver");
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Logger.info(Layer.DataAccess,"Database connected");
        } catch (SQLException e) {
            Logger.error(Layer.DataAccess, e);
        }
    }
}
