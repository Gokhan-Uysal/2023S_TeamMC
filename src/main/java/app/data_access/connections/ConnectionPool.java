package app.data_access.connections;

import app.common.EnvService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool implements Runnable {
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
        this.minInstance = Math.max(minInstance, 1);
        initMinConnections();
        Thread thread = new Thread(this);
        thread.start();
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
                e.printStackTrace();
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

    @Override
    public void run() {
        try{
            while (true){
                for (int i = minInstance; i < dbConnections.size(); i++){
                    Connection connection = dbConnections.get(i);
                    if (connection.isValid(1)){
                        connection.close();
                        dbConnections.remove(connection);
                    }
                }
                Thread.sleep(10000);
            }
        }
        catch (InterruptedException | SQLException e){
            e.printStackTrace();
        }
    }
}
