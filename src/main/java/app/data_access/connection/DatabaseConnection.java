package app.backend.connection;

import app.common.EnvService;
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
            System.out.println("Database connected");
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }
}
