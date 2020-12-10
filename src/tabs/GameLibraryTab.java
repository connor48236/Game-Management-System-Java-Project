package tabs;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pojo.*;
import tables.CategoryTable;
import tables.DevInfoTable;
import tables.GameTable;
import tables.PlatformTable;
import java.io.IOException;
/**
 * @author Connor Cozad
 */
public class GameLibraryTab extends Tab {

    // Create private instance variable.
    private static GameLibraryTab tab;

    // Grabs and localizes the gameTable
    private GameTable gameTable;

    // Connects gameTableView to a new TableView
    public TableView gameTableView;

    // Private constructor so GameLibraryTab is a singleton.
    private GameLibraryTab() throws IOException {
        this.setText("Game Library");

        // Grabs and localizes the gameTable
        gameTable = new GameTable();

        CategoryTable categoryTable = new CategoryTable();
        DevInfoTable devInfoTable = new DevInfoTable();
        PlatformTable platformTable = new PlatformTable();

        //connects gameTableView to a new TableView
        gameTableView = new TableView();

        //Creates the first column for the table for the name of the game
        TableColumn<PrettyGame, String> column1 = new TableColumn<>("Game Title");
        //Adds the game title to the column
        column1.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));
        //Adds the column to the table
        gameTableView.getColumns().add(column1);

        //Creates the column of the table for the image
        TableColumn<PrettyGame, TableCell> column2 = new TableColumn<>("Game Image");
        //Adds the game image to the column
        column2.setCellValueFactory(e -> {
            TableCell cell = new TableCell();

            Image image = e.getValue().getImage();
            ImageView gameImage = new ImageView();

            // Resize image and keep aspect ratio.
            double ratio = image.getHeight() / image.getWidth();
            double newHeight = 50;

            gameImage.setImage(image);
            gameImage.setFitHeight(newHeight);
            gameImage.setFitWidth(newHeight / ratio);

            // Set the cells graphic to the game image.
            cell.setGraphic(gameImage);

            return new SimpleObjectProperty<>(cell);

        });

        //Adds the column to the table
        gameTableView.getColumns().add(column2);

        //Creates the column of the table for the platform
        TableColumn<PrettyGame, String> column3 = new TableColumn<>("Game Platform");
        //Adds the game platform to the column
        column3.setCellValueFactory(e -> new SimpleStringProperty(platformTable.getPlatform(e.getValue().getPlatform()).getName()));
        //Adds the column to the table
        gameTableView.getColumns().add(column3);

        //Creates the column of the table for the Category
        TableColumn<PrettyGame, String> column4 = new TableColumn<>("Game Category");
        //Adds the game category to the column
        column4.setCellValueFactory(e -> new SimpleStringProperty(categoryTable.getCategory(e.getValue().getCategory()).getName()));
        //Adds the column to the table
       gameTableView.getColumns().add(column4);

        //Creates the column of the table for the release date
        TableColumn<PrettyGame, String> column5 = new TableColumn<>("Game Release Date");
        //Adds the game Date to the column
        column5.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getReleaseDate()));
        //Adds the column to the table
        gameTableView.getColumns().add(column5);

        //Creates the column of the table for the DevInfo
      TableColumn<PrettyGame, String> column6 = new TableColumn<>("Game Developer Info");
        //Adds the game dev info to the column
       column6.setCellValueFactory(e -> new SimpleStringProperty("Developer " + devInfoTable.getDevInfo(e.getValue().getDevInfo()).getDeveloperName() + " , " +
               "Publisher " + devInfoTable.getDevInfo(e.getValue().getDevInfo()).getPublisherName()));
        //Adds the column to the table
        gameTableView.getColumns().add(column6);

        //adds all items to gameTableView
        gameTableView.getItems().addAll(gameTable.getAllPrettyGames());


        //Added the gameTableView to the Screen
       this.setContent(gameTableView);
    }


    public void updateLibrary() {
        gameTableView.getItems().clear();
        gameTableView.getItems().addAll(gameTable.getAllPrettyGames());
        System.out.println("Library updated");
    }

    // Get instance method.
    public static GameLibraryTab getInstance() throws IOException {
        if (tab == null) {
            tab = new GameLibraryTab();
        }
        return tab;
    }
}
