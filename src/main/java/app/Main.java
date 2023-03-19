package app;

import app.data_access.connections.ConnectionPool;

public class Main {
    public static void main(String[] args) {
        ConnectionPool cp = new ConnectionPool(3);
    }
}