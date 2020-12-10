package daos;

import pojo.Game;

import java.util.ArrayList;

/**
 * @author Connor Cozad
 */

public interface GameDao {
    ArrayList<Game> getAllGames();
    public Game getGame(int id);
    public void deleteGame(int id);
    public void createGame(Game game);
}
