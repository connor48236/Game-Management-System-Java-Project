package daos;

import pojo.Game;

import java.util.ArrayList;

public interface GameDao {
    ArrayList<Game> getAllGames();
    public Game getGame(int id);
    public void deleteGame(Game game);
    public void createGame(Game game);
}
