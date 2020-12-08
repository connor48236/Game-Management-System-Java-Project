package tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pojo.Category;
import pojo.DevInfo;
import pojo.Game;
import pojo.Platform;
import tables.CategoryTable;
import tables.DevInfoTable;
import tables.GameTable;
import tables.PlatformTable;

import java.util.ArrayList;

public class GameLibraryTab extends Tab {

    // Create private instance variable.
    private static GameLibraryTab tab;
    //Creates the page int for changing games
    static int page = 0;

    // Private constructor so GameLibraryTab is a singleton.
    private GameLibraryTab() {
        this.setText("Game Library");
        //Grabs and localizes the gameTable
        GameTable gameTable = new GameTable();
        CategoryTable categoryTable = new CategoryTable();
        DevInfoTable devInfoTable = new DevInfoTable();
        PlatformTable platformTable = new PlatformTable();

        //connects gameTableView to a new TableView
        TableView gameTableView = new TableView();

        //Creates the first column for the table for the name of the game
        TableColumn<Game, String> column1 = new TableColumn<>("Game Title");
        //Adds the game title to the column
        column1.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));
        //Adds the column to the table
        gameTableView.getColumns().add(column1);

        //Creates the column of the table for the image
        TableColumn<Game, String> column2 = new TableColumn<>("Game Image");
        //Adds the game image to the column
        column2.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getImage()));
        //Adds the column to the table
        gameTableView.getColumns().add(column2);

        //Creates the column of the table for the platform
        TableColumn<Game, String> column3 = new TableColumn<>("Game Platform");
        //Adds the game platform to the column
        column3.setCellValueFactory(e -> new SimpleStringProperty(platformTable.getPlatform(e.getValue().getId()).getName()));
        //Adds the column to the table
        gameTableView.getColumns().add(column3);

        //Creates the column of the table for the Category
        TableColumn<Game, String> column4 = new TableColumn<>("Game Category");
        //Adds the game category to the column
        column4.setCellValueFactory(e -> new SimpleStringProperty(categoryTable.getCategory(e.getValue().getId()).getName()));
        //Adds the column to the table
       gameTableView.getColumns().add(column4);

        //Creates the column of the table for the release date
        TableColumn<Game, String> column5 = new TableColumn<>("Game Release Date");
        //Adds the game Date to the column
        column5.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getReleaseDate()));
        //Adds the column to the table
        gameTableView.getColumns().add(column5);

        //Creates the column of the table for the DevInfo
      TableColumn<Game, String> column6 = new TableColumn<>("Game Developer Info");
        //Adds the game dev info to the column
       column6.setCellValueFactory(e -> new SimpleStringProperty("Developer " + devInfoTable.getDevInfo(e.getValue().getId()).getDeveloperName() + " , " +
               "Publisher " + devInfoTable.getDevInfo(e.getValue().getId()).getPublisherName()));
        //Adds the column to the table
       gameTableView.getColumns().add(column6);

       //adds all items to gameTableView
        gameTableView.getItems().addAll(gameTable.getAllGames());


        //Added the gameTableView to the Screen
       this.setContent(gameTableView);
    }



    // Get instance method.
    public static GameLibraryTab getInstance() {
        if (tab == null) {
            tab = new GameLibraryTab();
        }
        return tab;
    }
}
