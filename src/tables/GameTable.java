package tables;

import daos.GameDao;
import database.DBConst;
import database.Database;
import pojo.DevInfo;
import pojo.Game;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GameTable  implements GameDao {
    Database database = Database.getInstance();
    ArrayList<Game> games;


    @Override
    public ArrayList<Game> getAllGames() {
        String sql = "SELECT * FROM " + DBConst.TABLE_GAME;
        games = new ArrayList<Game>();
        try {
            Statement getGames = database.getConnection().createStatement();
            ResultSet data = getGames.executeQuery(sql);
            while (data.next()){
                games.add(new Game(data.getInt(DBConst.GAME_COLUMN_ID),
                        data.getString(DBConst.GAME_COLUMN_NAME),
                        data.getString(DBConst.GAME_COLUMN_DEV_INFO),
                        data.getInt(DBConst.GAME_COLUMN_RELEASE_DATE)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public Game getGame(int id) {
        String sql = "SELECT * FROM " + DBConst.GAME_COLUMN_ID + " WHERE " + DBConst.GAME_CATEGORY_COLUMN_ID + " = " + id;
        Game game = new Game();

        try {
            Statement getGame = database.getConnection().createStatement();
            ResultSet data = getGame.executeQuery(sql);

            while (data.next()) {
                games.add(new Game(data.getInt(DBConst.GAME_COLUMN_ID),
                        data.getString(DBConst.GAME_COLUMN_NAME),
                        data.getString(DBConst.GAME_COLUMN_DEV_INFO),
                        data.getInt(DBConst.GAME_COLUMN_RELEASE_DATE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

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

    @Override
    public void createGame(Game game) {
        String sql = "INSERT INTO " + DBConst.TABLE_GAME +
                "(" + DBConst.GAME_COLUMN_NAME + "," +
                DBConst.GAME_CATEGORY_COLUMN_CATEGORY + "," +
                DBConst.GAME_COLUMN_DEV_INFO + ") VALUES ('" +
                game.getName() + "','" + game.getReleaseDate() + "','" + game.getDevInfo() + "')";
        try {
            database.getConnection().createStatement().execute(sql);
            System.out.println("Inserted Record");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
