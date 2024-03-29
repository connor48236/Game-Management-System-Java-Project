package database;

/**
 * @author Chris Corbett
 * holds all the constants to create the
 * tables for the database.
 */
public class DBConst {

    // Game Table
    public static final String TABLE_GAME = "game";
    public static final String GAME_COLUMN_ID = "id";
    public static final String GAME_COLUMN_NAME = "name";
    public static final String GAME_COLUMN_IMAGE = "image";
    public static final String GAME_COLUMN_PLATFORM = "platform";
    public static final String GAME_COLUMN_CATEGORY = "category";
    public static final String GAME_COLUMN_RELEASE_DATE = "release_date";
    public static final String GAME_COLUMN_DEV_INFO = "dev_info";

    // DevInfo Table
    public static final String TABLE_DEV_INFO = "dev_info";
    public static final String DEV_INFO_COLUMN_ID = "id";
    public static final String DEV_INFO_COLUMN_DEVELOPER_NAME = "developer_name";
    public static final String DEV_INFO_COLUMN_PUBLISHER_NAME = "publisher_name";

    // Platform Table
    public static final String TABLE_PLATFORM = "platform";
    public static final String PLATFORM_COLUMN_ID = "id";
    public static final String PLATFORM_COLUMN_NAME = "name";

    // Category Table
    public static final String TABLE_CATEGORY = "category";
    public static final String CATEGORY_COLUMN_ID = "id";
    public static final String CATEGORY_COLUMN_NAME = "name";

    // CREATE STATEMENTS
    // Create DevInfo Table
    public static final String CREATE_TABLE_DEV_INFO = "CREATE TABLE " + TABLE_DEV_INFO + " (" +
            DEV_INFO_COLUMN_ID + " INT NOT NULL AUTO_INCREMENT, " +
            DEV_INFO_COLUMN_DEVELOPER_NAME + " VARCHAR(64), " +
            DEV_INFO_COLUMN_PUBLISHER_NAME + " VARCHAR(64), " +
            "PRIMARY KEY(" + DEV_INFO_COLUMN_ID + "))";

    // Create Platform Table
    public static final String CREATE_TABLE_PLATFORM = "CREATE TABLE " + TABLE_PLATFORM + " (" +
            PLATFORM_COLUMN_ID + " INT NOT NULL AUTO_INCREMENT, " +
            PLATFORM_COLUMN_NAME + " VARCHAR(32), " +
            "PRIMARY KEY(" + PLATFORM_COLUMN_ID + "))";

    // Create Category Table
    public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " (" +
            CATEGORY_COLUMN_ID + " INT NOT NULL AUTO_INCREMENT, " +
            CATEGORY_COLUMN_NAME + " VARCHAR(64), " +
            "PRIMARY KEY(" + CATEGORY_COLUMN_ID + "))";

    // Create Game Table
    public static final String CREATE_TABLE_GAME = "CREATE TABLE " + TABLE_GAME + " (" +
            GAME_COLUMN_ID + " INT NOT NULL AUTO_INCREMENT, " +
            GAME_COLUMN_NAME + " VARCHAR(256), " +
            GAME_COLUMN_IMAGE + " VARCHAR(256), " +
            GAME_COLUMN_PLATFORM + " INT NOT NULL, " +
            GAME_COLUMN_CATEGORY + " INT NOT NULL, " +
            GAME_COLUMN_RELEASE_DATE + " DATE, " +
            GAME_COLUMN_DEV_INFO + " INT NOT NULL, " +
            "FOREIGN KEY(" + GAME_COLUMN_DEV_INFO + ") REFERENCES " + TABLE_DEV_INFO + "(" + DEV_INFO_COLUMN_ID + "), " +
            "FOREIGN KEY(" + GAME_COLUMN_PLATFORM + ") REFERENCES " + TABLE_PLATFORM + "(" + PLATFORM_COLUMN_ID + "), " +
            "FOREIGN KEY(" + GAME_COLUMN_CATEGORY + ") REFERENCES " + TABLE_CATEGORY + "(" + CATEGORY_COLUMN_ID + "), " +
            "PRIMARY KEY(" + GAME_COLUMN_ID + "))";


}
