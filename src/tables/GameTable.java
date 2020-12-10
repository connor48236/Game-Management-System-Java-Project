package tables;

import daos.GameDao;
import database.DBConst;
import database.Database;
import javafx.scene.image.Image;
import pojo.Game;
import pojo.PrettyGame;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Connor Cozad
 */

public class GameTable  implements GameDao {
    //Connects to the dataBase
    Database database = Database.getInstance();
    //Creates a arrayList of Games
    ArrayList<Game> games;

    public GameTable() throws IOException {
    }

    /**
     *
     * @return This will find all games in the database and return them
     */
    public ArrayList<Game> getAllGames() {
        String sql = "SELECT * FROM " + DBConst.TABLE_GAME;
        games = new ArrayList<Game>();
        try {
            Statement getGames = database.getConnection().createStatement();
            ResultSet data = getGames.executeQuery(sql);
            while (data.next()){
                games.add(new Game(data.getInt(DBConst.GAME_COLUMN_ID),
                        data.getString(DBConst.GAME_COLUMN_NAME),
                        data.getString(DBConst.GAME_COLUMN_IMAGE),
                        data.getInt(DBConst.GAME_COLUMN_PLATFORM),
                        data.getInt(DBConst.GAME_COLUMN_CATEGORY),
                        data.getString(DBConst.GAME_COLUMN_RELEASE_DATE),
                        data.getInt(DBConst.GAME_COLUMN_DEV_INFO)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return games;
    }

    /**
     *
     * @return This will find all games in the database and return them
     */
    public ArrayList<PrettyGame> getAllPrettyGames() {
        String sql = "SELECT * FROM " + DBConst.TABLE_GAME;
        ArrayList<PrettyGame> prettyGames = new ArrayList<>();
        try {
            Statement getGames = database.getConnection().createStatement();
            ResultSet data = getGames.executeQuery(sql);
            while (data.next()){
                prettyGames.add(new PrettyGame(data.getInt(DBConst.GAME_COLUMN_ID),
                        data.getString(DBConst.GAME_COLUMN_NAME),
                        new Image(String.valueOf(new File(data.getString(DBConst.GAME_COLUMN_IMAGE)).toURI())),
                        data.getInt(DBConst.GAME_COLUMN_PLATFORM),
                        data.getInt(DBConst.GAME_COLUMN_CATEGORY),
                        data.getString(DBConst.GAME_COLUMN_RELEASE_DATE),
                        data.getInt(DBConst.GAME_COLUMN_DEV_INFO)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return prettyGames;
    }

    /**
     *
     * @param id The id lets you enter a id and the function will compare it with game id's looking for a match
     * @return Will return a game based on the id given
     */
    @Override
    public Game getGame(int id) {
        String sql = "SELECT * FROM " + DBConst.TABLE_GAME + " WHERE " + DBConst.GAME_COLUMN_ID + " = " + id;
        Game game = new Game();

        try {
            Statement getGame = database.getConnection().createStatement();
            ResultSet data = getGame.executeQuery(sql);

            while (data.next()) {
                games.add(new Game(data.getInt(DBConst.GAME_COLUMN_ID),
                        data.getString(DBConst.GAME_COLUMN_NAME),
                        data.getString(DBConst.GAME_COLUMN_IMAGE),
                        data.getInt(DBConst.GAME_COLUMN_CATEGORY),
                        data.getInt(DBConst.GAME_COLUMN_PLATFORM),
                        data.getString(DBConst.GAME_COLUMN_DEV_INFO),
                        data.getInt(DBConst.GAME_COLUMN_RELEASE_DATE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

    /**
     *
     * @param id Will allow the user to delete a game based on the id
     */
    @Override
    public void deleteGame(int id) {
        String sql = "DELETE FROM " + DBConst.TABLE_GAME + " WHERE " + DBConst.GAME_COLUMN_ID + " = " + id;
        try{
            database.getConnection().createStatement().execute(sql);
            System.out.println("Deleted Game");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @param game This will allow the user to insert a new game into the dataBase
     */
    @Override
    public void createGame(Game game) {
        String sql = "INSERT INTO " + DBConst.TABLE_GAME +
                "(" + DBConst.GAME_COLUMN_NAME + "," +
                DBConst.GAME_COLUMN_IMAGE + "," +
                DBConst.GAME_COLUMN_PLATFORM + "," +
                DBConst.GAME_COLUMN_CATEGORY + "," +
                DBConst.GAME_COLUMN_RELEASE_DATE + "," +
                DBConst.GAME_COLUMN_DEV_INFO + ") VALUES ('" +
                game.getName() + "','" + game.getImage() + "','" + game.getPlatform() + "','" + game.getCategory() +"','" + game.getReleaseDate() + "','" + game.getDevInfo() + "')";
        try {
            database.getConnection().createStatement().execute(sql);
            System.out.println("Inserted Record");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getCategoryCount(int category) {
        int count = -1;
        try {
            PreparedStatement getCount = database.getConnection()
                    .prepareStatement("SELECT * FROM " + DBConst.TABLE_GAME + " WHERE " +
                            DBConst.GAME_COLUMN_CATEGORY + " = '" + category + "'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet data = getCount.executeQuery();
            data.last();
            count = data.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getPlatformCount(int platform) {
        int count = -1;
        try {
            PreparedStatement getCount = database.getConnection()
                    .prepareStatement("SELECT * FROM " + DBConst.TABLE_GAME + " WHERE " +
                            DBConst.GAME_COLUMN_PLATFORM + " = '" + platform + "'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet data = getCount.executeQuery();
            data.last();
            count = data.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
