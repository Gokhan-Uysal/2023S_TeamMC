package app.backend.connections;

import app.common.EnvService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String connectionUrl = String.format(
            "jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
            EnvService.getEnv("POSTGRES_HOST"),
            EnvService.getEnv("POSTGRES_PORT"),
            EnvService.getEnv("POSTGRES_DATABASE"),
            EnvService.getEnv("POSTGRES_USER"),
            EnvService.getEnv("POSTGRES_PASSWORD")
            );
    private Connection connection;
    public Connection getConnection() {
        return connection;
    }

    public DatabaseConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load PostgresSQL JDBC driver", e);
        }
        try {
            this.connection = DriverManager.getConnection(this.connectionUrl);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
