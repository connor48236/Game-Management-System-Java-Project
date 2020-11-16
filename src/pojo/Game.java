package pojo;

public class Game {
    private int id;
    private String name;
    private String releaseDate;
    private int devInfo;

    // Constructors
    public Game() {
    }

    public Game(String name, String releaseDate, int devInfo) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.devInfo = devInfo;
    }

    public Game(int id, String name, String releaseDate, int devInfo) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.devInfo = devInfo;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDevInfo() {
        return devInfo;
    }

    public void setDevInfo(int devInfo) {
        this.devInfo = devInfo;
    }
}
