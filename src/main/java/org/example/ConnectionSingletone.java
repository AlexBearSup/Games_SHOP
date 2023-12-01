package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingletone {
    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "125410";

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GameShopData", name, password);
        }
        return connection;
    }

}

