package pojo;

public class GameCategory {
    private int id;
    private int gameId;
    private int categoryId;

    // Constructors
    public GameCategory() {
    }

    public GameCategory(int gameId, int categoryId) {
        this.gameId = gameId;
        this.categoryId = categoryId;
    }

    public GameCategory(int id, int gameId, int categoryId) {
        this.id = id;
        this.gameId = gameId;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
