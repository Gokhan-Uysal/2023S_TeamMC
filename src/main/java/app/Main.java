package app;

import app.data_access.connections.ConnectionPool;
import app.data_access.repositories.BaseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionPool cp = new ConnectionPool(1);
    }
}