package app.data_access.connections;

import app.common.EnvService;
import app.common.Layer;
import app.common.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool implements Runnable {
    private static final ArrayList<Connection> dbConnections = new ArrayList<>();
    private static final int maxInstance = 10;

    private static final String dbConnectionUrl = String.format(
            "jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
            EnvService.getEnv("POSTGRES_HOST"),
            EnvService.getEnv("POSTGRES_PORT"),
            EnvService.getEnv("POSTGRES_DATABASE"),
            EnvService.getEnv("POSTGRES_USER"),
            EnvService.getEnv("POSTGRES_PASSWORD")
    );

    public ConnectionPool(){
        Thread thread = new Thread(this);
        thread.start();
    }

    private static Connection newConnection() throws  NullPointerException{
        DatabaseConnection db = new DatabaseConnection(dbConnectionUrl);
        Connection connection = db.getConnection();
        if (connection != null){
            return  db.getConnection();
        }
        throw new NullPointerException("Connection is missing");
    }

    private static Connection addConnectionToPool(Connection connection){
        if (dbConnections.size() >= maxInstance){
            Logger.error(Layer.DataAccess, "Database connection pool is full");
            return null;
        }
        dbConnections.add(connection);
        return connection;
    }

    private static Connection findValidConnection(){
        for(Connection connection : dbConnections){
            try{
                if (connection.isValid(2)){
                    return connection;
                }
            }
            catch (SQLException e){
                Logger.error(Layer.DataAccess, e);
            }
        }
        return null;
    }

    public static Connection getValidConnection(){
        Connection connection = findValidConnection();
        if (connection != null){
            return connection;
        }
        return addConnectionToPool(newConnection());
    }

    @Override
    public void run() {
        try{
            while (true){
                for (int i = 0; i < dbConnections.size(); i++){
                    Connection connection = dbConnections.get(i);
                    if (connection.isValid(5)){
                        connection.close();
                        dbConnections.remove(connection);
                    }
                }
                Thread.sleep(5000);
                Logger.info(Layer.DataAccess, String.format("Active connections: %s", dbConnections.toString()));
            }
        }
        catch (InterruptedException | SQLException e){
            e.printStackTrace();
        }
    }
}
