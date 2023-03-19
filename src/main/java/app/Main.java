package app;

import app.backend.connection.ConnectionPool;
import app.backend.connection.DatabaseConnection;
import app.common.EnvService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        new ConnectionPool(2);
    }

}