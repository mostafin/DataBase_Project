package sample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Database {

    private static Database instance = new Database();

    public Connection getConnection() {
        return connection;
    }

    private Connection connection = null;
    private Database(){};

    public static Database getInstance() {
        return instance;
    }

    public Connection connect(){
        try {
            System.out.println("Łączenie");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/products._db","root","");
            System.out.println("Połaczono");
            return connection;
        } catch (SQLException e) {
            System.out.println("Nie udało sie połączyć: " +e.getMessage());
            return null;
        }
    }

    public void disconnect(){
        try {
            connection.close();
            System.out.println("Rozłaczono");
        } catch (SQLException e) {
            System.out.println("Próba zamkniecie nieudana: "+ e.getMessage());
        }
    }
}
