package pojo;

import javafx.scene.image.Image;

public class Game {
    private int id;
    private String name;
    private String releaseDate;
    private int devInfo;
    private Image gameImg;

    // Constructors
    public Game() {
    }

    public Game(String name, String releaseDate, int devInfo, Image gameImg) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.devInfo = devInfo;
        this.gameImg = gameImg;
    }

    public Game(int id, String name, String releaseDate, int devInfo, Image gameImg) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.devInfo = devInfo;
        this.gameImg = gameImg;
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

    public Image getGameImg() {
        return gameImg;
    }

    public void setGameImg(Image gameImg) {
        this.gameImg = gameImg;
    }
}
