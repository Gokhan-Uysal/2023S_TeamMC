package app.backend.connection;

import app.common.EnvService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    private static final ArrayList<Connection> dbConnections = new ArrayList<>();
    private final int minInstance;

    private static final String dbConnectionUrl = String.format(
            "jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
            EnvService.getEnv("POSTGRES_HOST"),
            EnvService.getEnv("POSTGRES_PORT"),
            EnvService.getEnv("POSTGRES_DATABASE"),
            EnvService.getEnv("POSTGRES_USER"),
            EnvService.getEnv("POSTGRES_PASSWORD")
    );

    public ConnectionPool(int minInstance){
        this.minInstance = minInstance;
        initMinConnections();
    }

    public void initMinConnections(){
        for (int i = 0; i < minInstance; i++){
            addConnectionToPool(newConnection());
        }
    }

    public static Connection newConnection(){
        DatabaseConnection db = new DatabaseConnection(dbConnectionUrl);
        return  db.getConnection();
    }

    public static Connection addConnectionToPool(Connection connection){
        dbConnections.add(connection);
        return connection;
    }

    public static Connection findValidConnection(){
        for(Connection connection : dbConnections){
            try{
                if (connection.isValid(5)){
                    return connection;
                }
            }
            catch (SQLException e){
                System.out.println("Connection error: " + e.getMessage());
            }
        }
        return null;
    }

    public static Connection getValidConnection(){
        Connection connection = findValidConnection();
        if (connection != null){
            return connection;
        }
        return addConnectionToPool(connection);
    }

}
