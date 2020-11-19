package daos;

import pojo.Game;

import java.util.ArrayList;

public interface GameDao {
    ArrayList<Game> getAllGames();
    public Game getGame(int id);
    public void updateGame(Game game);
    public void deleteGame(Game game);
    public void createGame(Game game);
}
