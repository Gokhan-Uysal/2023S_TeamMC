package app;

import app.common.Logger;
import app.data_access.connections.ConnectionPool;

public class Main {
    public static void main(String[] args) {
        ConnectionPool cp = new ConnectionPool();
        ConnectionPool.getValidConnection();
    }
}