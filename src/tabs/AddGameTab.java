
package tabs;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.GameTracker;
import pojo.Category;
import pojo.Platform;
import tables.CategoryTable;
import tables.DevInfoTable;
import tables.GameTable;
import tables.PlatformTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddGameTab extends Tab {

    // Create private instance variable.
    private static AddGameTab tab;

    // Private constructor so AddGameTab is a singleton.
    private AddGameTab() {
        this.setText("Add Game");

        //Adding the tables to the class to use them
        CategoryTable categoryTable = new CategoryTable();
        DevInfoTable devInfoTable = new DevInfoTable();
        GameTable gameTable = new GameTable();
        PlatformTable platformTable = new PlatformTable();

        GridPane root = new GridPane();

        //Text and TextField for adding games
        Text nameOfGame = new Text("Name Of Game");
        root.add(nameOfGame, 0, 0);
        TextField userGameName = new TextField();
        userGameName.setPromptText("Enter Name of Game here");
        root.add(userGameName, 1, 0);

        //Text and ComboBox for adding Category
        Text categoryText = new Text("Category");
        root.add(categoryText, 2, 0);
        ComboBox<Category> comboCategory = new ComboBox<>();
        comboCategory.setItems(FXCollections.observableList(categoryTable.getAllCategories()));
        root.add(comboCategory, 3, 0);

        //Text and textField for the development company
        Text developmentCompany = new Text("Enter the Development company");
        root.add(developmentCompany, 4, 0);
        TextField userDevCompany = new TextField();
        userDevCompany.setPromptText("Enter development company here");
        root.add(userDevCompany, 5, 0);

        //Text and ComboBox for platform
        Text platform = new Text("Platform");
        root.add(platform, 6, 0);
        ComboBox<Platform> comboPlatform = new ComboBox<>();
        comboPlatform.setItems(FXCollections.observableList(platformTable.getAllPlatforms()));
        root.add(comboPlatform, 7, 0);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");


        Button imageButton = new Button("Select Image");
        imageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(GameTracker.getStage());
            try {
                getImage(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        root.add(imageButton, 8, 0);

        this.setContent(root);
    }

    private void getImage(File file) throws IOException {
        // Path for the image.
        File newFile = new File("src/images/" + file.getName());

        try {
            // Make sure the images directory exists.
            Files.createDirectory(Paths.get("src/images"));
        } catch (FileAlreadyExistsException e) {
            // Images directory exists.
        }

        // Move the image to the images directory
        Files.move(Paths.get(file.toURI()), Paths.get(newFile.toURI()));
    }

    // Get instance method.
    public static AddGameTab getInstance() {
        if (tab == null) {
            tab = new AddGameTab();
        }
        return tab;
    }
}
