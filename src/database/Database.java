package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    // Instance variable for singleton
    private static Database instance = null;
    private Connection connection = null;

    // Private constructor
    private Database() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Login.DB_NAME + "?serverTimezone=UTC", Login.DB_USER, Login.DB_PASSWORD);
                System.out.println("Created Connection");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // getInstance method
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }
}
