package pojo;

public class DevInfo {
    private int id;
    private String developerName;
    private String publisherName;

    // Constructors
    public DevInfo() {
    }

    public DevInfo(String developerName, String publisherName) {
        this.developerName = developerName;
        this.publisherName = publisherName;
    }

    public DevInfo(int id, String developerName, String publisherName) {
        this.id = id;
        this.developerName = developerName;
        this.publisherName = publisherName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "Developer: " + developerName + ", Publisher: " + publisherName;
    }
}
