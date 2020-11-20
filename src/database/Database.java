package database;

import java.sql.*;

public class Database {
    // Instance variable for singleton
    private static Database instance = null;
    private Connection connection = null;

    // Private constructor
    private Database() {
        if (connection == null) {
            try {
                // Create connection to the server
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Login.DB_NAME + "?serverTimezone=UTC", Login.DB_USER, Login.DB_PASSWORD);
                System.out.println("Created Connection");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                // Create tables for the user to use.
                createTable(DBConst.TABLE_DEV_INFO, DBConst.CREATE_TABLE_DEV_INFO, connection);
                createTable(DBConst.TABLE_GAME, DBConst.CREATE_TABLE_GAME, connection);
                createTable(DBConst.TABLE_PLATFORM, DBConst.CREATE_TABLE_PLATFORM, connection);
                createTable(DBConst.TABLE_GAME_PLATFORM, DBConst.CREATE_TABLE_GAME_PLATFORM, connection);
                createTable(DBConst.TABLE_CATEGORY, DBConst.CREATE_TABLE_CATEGORY, connection);
                createTable(DBConst.TABLE_GAME_CATEGORY, DBConst.CREATE_TABLE_GAME_CATEGORY, connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // getInstance method for singleton
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    // Create table method to create table in database
    public void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTables;
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, tableName, null);
        if (resultSet.next()) {
            System.out.println(tableName + " Table already exists!");
        } else {
            createTables = connection.createStatement();
            createTables.execute(tableQuery);
            System.out.println(tableName + " Table has been created successfully!");
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
