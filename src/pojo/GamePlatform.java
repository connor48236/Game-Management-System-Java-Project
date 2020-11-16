package pojo;

public class GamePlatform {
    private int id;
    private int gameId;
    private int platformId;

    // Constructors
    public GamePlatform() {
    }

    public GamePlatform(int gameId, int platformId) {
        this.gameId = gameId;
        this.platformId = platformId;
    }

    public GamePlatform(int id, int gameId, int platformId) {
        this.id = id;
        this.gameId = gameId;
        this.platformId = platformId;
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

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
}
