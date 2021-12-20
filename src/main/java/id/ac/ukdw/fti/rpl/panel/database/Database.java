package id.ac.ukdw.fti.rpl.panel.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    private Connection connection = null;
    final private String url = "jdbc:sqlite:vizbible.sqlite";

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            return connection;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
    }
}
