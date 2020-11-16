package daos;

import pojo.Game;

import java.util.ArrayList;

public interface GameDao {
    ArrayList<Game> getAllGames();
    Game getGame(int id);
}
