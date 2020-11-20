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
                        data.getString(DBConst.GAME_CATEGORY_COLUMN_CATEGORY),
                        data.getInt(DBConst.GAME_COLUMN_DEV_INFO)));
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
                        data.getString(DBConst.GAME_CATEGORY_COLUMN_CATEGORY),
                        data.getInt(DBConst.GAME_COLUMN_DEV_INFO)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGame(Game game) {

    }

    @Override
    public void createGame(Game game) {

    }


}
