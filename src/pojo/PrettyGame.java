package pojo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PrettyGame {
    private int id;
    private String name;
    private Image image;
    private int platform;
    private int category;
    private String releaseDate;
    private int devInfo;

    // Constructors
    public PrettyGame() {
    }

    public PrettyGame(String name, Image image, int platform, int category, String releaseDate, int devInfo) {
        this.name = name;
        this.image = image;
        this.platform = platform;
        this.category = category;
        this.releaseDate = releaseDate;
        this.devInfo = devInfo;
    }

    public PrettyGame(int id, String name, Image image, int platform, int category, String releaseDate, int devInfo) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.platform = platform;
        this.category = category;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    @Override
    public String toString() {
        return getName();
    }
}
