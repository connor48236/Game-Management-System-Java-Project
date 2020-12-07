package tabs;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.GameTracker;
import pojo.Category;
import pojo.DevInfo;
import pojo.Game;
import pojo.Platform;
import tables.CategoryTable;
import tables.DevInfoTable;
import tables.GameTable;
import tables.PlatformTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddGameTab extends Tab {

    // Create private instance variable.
    private static AddGameTab tab;

    private String imagePath;

    // Private constructor so AddGameTab is a singleton.
    private AddGameTab() throws IOException {
        this.setText("Add Game");

        //Adding the tables to the class to use them
        CategoryTable categoryTable = new CategoryTable();
        DevInfoTable devInfoTable = new DevInfoTable();
        GameTable gameTable = new GameTable();
        PlatformTable platformTable = new PlatformTable();

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));

        //Text and TextField for adding games
        Text nameOfGame = new Text("Name Of Game");
        root.add(nameOfGame, 0, 0);
        TextField gameName = new TextField();
        gameName.setPromptText("Enter Name of Game here");
        root.add(gameName, 1, 0);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Create image input
        Text gameImageText = new Text("Select Image");
        root.add(gameImageText, 0, 1);
        Button imageButton = new Button("Select Image");

        imageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(GameTracker.getStage());
            try {
                imagePath = getImage(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        root.add(imageButton, 1, 1);

        // Create release year input
        Text gameReleaseYearText = new Text("Game Release Year");
        root.add(gameReleaseYearText, 0, 2);
        DatePicker gameReleaseYear = new DatePicker();
        root.add(gameReleaseYear, 1, 2);

        // DevInfo ComboBox
        Text gameDevInfo = new Text("Developer and Publisher");
        root.add(gameDevInfo, 0, 3);
        ComboBox<DevInfo> devInfoComboBox = new ComboBox<>();
        devInfoComboBox.setItems(FXCollections.observableArrayList(devInfoTable.getAllDevInfo()));
        root.add(devInfoComboBox, 1, 3);

        // Create devInfo button
        Button createDevInfo = new Button("Add New Developer and Publisher");
        createDevInfo.setOnAction(e -> {
            // Create grid pane for new window
            GridPane createDevInfoGrid = new GridPane();
            createDevInfoGrid.setHgap(10);
            createDevInfoGrid.setVgap(10);
            createDevInfoGrid.setPadding(new Insets(10, 10, 10, 10));

            // Create Developer name input
            Text developerNameText = new Text("Developer");
            createDevInfoGrid.add(developerNameText, 0, 0);
            TextField developerName = new TextField();
            createDevInfoGrid.add(developerName, 1, 0);

            // Create Publisher name input
            Text publisherNameText = new Text("Publisher");
            createDevInfoGrid.add(publisherNameText, 0, 1);
            TextField publisherName = new TextField();
            createDevInfoGrid.add(publisherName, 1, 1);

            // Create create button
            Button addDevInfo = new Button("Create DevInfo");
            createDevInfoGrid.add(addDevInfo, 0, 2);

            Scene createPlatformScene = new Scene(createDevInfoGrid, 400, 300);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Create Platform");
            newWindow.setScene(createPlatformScene);
            newWindow.show();

            addDevInfo.setOnAction(event -> {
                DevInfo devInfo = new DevInfo(developerName.getText(), publisherName.getText());
                devInfoTable.createDevInfo(devInfo);
                newWindow.close();
            });
        });
        root.add(createDevInfo, 2, 3);

        //Text and ComboBox for platform
        Text gamePlatformText = new Text("Platform");
        root.add(gamePlatformText, 0, 4);
        ComboBox<Platform> gamePlatform = new ComboBox<>();
        gamePlatform.setItems(FXCollections.observableArrayList(platformTable.getAllPlatforms()));
        root.add(gamePlatform, 1, 4);

        // Create create platform button
        Button createPlatform = new Button("Add New Platform");
        createPlatform.setOnAction(e -> {
            // Create grid pane for new window
            GridPane createPlatformGrid = new GridPane();
            createPlatformGrid.setHgap(10);
            createPlatformGrid.setVgap(10);
            createPlatformGrid.setPadding(new Insets(10, 10, 10, 10));

            // Create platform name input
            Text platformNameText = new Text("Platform");
            createPlatformGrid.add(platformNameText, 0, 0);
            TextField platformName = new TextField();
            createPlatformGrid.add(platformName, 1, 0);

            // Create create button
            Button addPlatform = new Button("Create Platform");
            createPlatformGrid.add(addPlatform, 0, 1);

            Scene createDevInfoScene = new Scene(createPlatformGrid, 400, 300);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Create DevInfo");
            newWindow.setScene(createDevInfoScene);
            newWindow.show();

            addPlatform.setOnAction(event -> {
                Platform platform = new Platform(platformName.getText());
                platformTable.createPlatform(platform);
                newWindow.close();
            });
        });
        root.add(createPlatform, 2, 4);

        //Text and ComboBox for adding Category
        Text gameCategoriesText = new Text("Category");
        root.add(gameCategoriesText, 0, 5);
        ComboBox<Category> gameCategory = new ComboBox<>();
        gameCategory.setItems(FXCollections.observableArrayList(categoryTable.getAllCategories()));
        root.add(gameCategory, 1, 5);

        // Create create category button
        Button createCategory = new Button("Add New Category");
        createCategory.setOnAction(e -> {
            // Create grid pane for new window
            GridPane createCategoryGrid = new GridPane();
            createCategoryGrid.setHgap(10);
            createCategoryGrid.setVgap(10);
            createCategoryGrid.setPadding(new Insets(10, 10, 10, 10));

            // Create category name input
            Text categoryNameText = new Text("Category");
            createCategoryGrid.add(categoryNameText, 0, 0);
            TextField categoryName = new TextField();
            createCategoryGrid.add(categoryName, 1, 0);

            // Create create button
            Button addCategory = new Button("Create Category");
            createCategoryGrid.add(addCategory, 0, 1);

            Scene createCategoryScene = new Scene(createCategoryGrid, 400, 300);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Create Category");
            newWindow.setScene(createCategoryScene);
            newWindow.show();

            addCategory.setOnAction(event -> {
                Category category = new Category(categoryName.getText());
                categoryTable.createCategory(category);
                newWindow.close();
            });
        });
        root.add(createCategory, 2, 5);

        Button addGameButton = new Button("Add Game");
        addGameButton.setOnAction(event -> {
            Game game = new Game(
                    gameName.getText(),
                    imagePath,
                    gamePlatform.getSelectionModel().getSelectedItem().getId(),
                    gameCategory.getSelectionModel().getSelectedItem().getId(),
                    gameReleaseYear.getValue().toString(),
                    devInfoComboBox.getSelectionModel().getSelectedItem().getId()
            );
            System.out.println(gamePlatform.getSelectionModel().getSelectedItem().getId());
            gameTable.createGame(game);
        });
        root.add(addGameButton, 0, 6);

        this.setContent(root);
    }

    private String getImage(File file) throws IOException {
        // Path for the image.
        if (file != null) {
            File newFile = new File("src/images/" + file.getName());

            try {
                // Make sure the images directory exists.
                Files.createDirectory(Paths.get("src/images"));
            } catch (FileAlreadyExistsException e) {
                // Images directory exists.
            }

            // Move the image to the images directory
            Files.move(Paths.get(file.toURI()), Paths.get(newFile.toURI()));

            return newFile.getAbsolutePath();
        }
        return null;
    }

    // Get instance method.
    public static AddGameTab getInstance() throws IOException {
        if (tab == null) {
            tab = new AddGameTab();
        }
        return tab;
    }
}