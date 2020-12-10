package database;

import java.io.IOException;
import java.sql.*;

/**
 * @author Chris Corbett
 * creates connection to the data base and creates tables
 * if they don't exist.
 */
public class Database {
    // Instance variable for singleton
    private static Database instance = null;
    private Connection connection = null;

    // Private constructor
    private Database() throws IOException {

        String[] loginInfo = GetLogin.getLoginInfo();

        String DB_NAME = "";
        String DB_USER = "";
        String DB_PASSWORD = "";
        String DB_LOCATION = "";

        if (loginInfo != null) {
            DB_NAME = loginInfo[0];
            DB_USER = loginInfo[1];
            DB_PASSWORD = loginInfo[2];
            DB_LOCATION = loginInfo[3];


            if (connection == null) {
                try {
                    // Create connection to the server
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://" + DB_LOCATION + "/" + DB_NAME + "?serverTimezone=UTC", DB_USER, DB_PASSWORD);
                    System.out.println("Created Connection");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    // Create tables for the user to use.
                    createTable(DBConst.TABLE_DEV_INFO, DBConst.CREATE_TABLE_DEV_INFO, connection);
                    createTable(DBConst.TABLE_PLATFORM, DBConst.CREATE_TABLE_PLATFORM, connection);
                    createTable(DBConst.TABLE_CATEGORY, DBConst.CREATE_TABLE_CATEGORY, connection);
                    createTable(DBConst.TABLE_GAME, DBConst.CREATE_TABLE_GAME, connection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // getInstance method for singleton
    public static Database getInstance() throws IOException {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    // Create table method to create table in database
    public void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTables;
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(Login.DB_NAME, null, tableName, null);
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
