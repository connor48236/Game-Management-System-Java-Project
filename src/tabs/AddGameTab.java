package tabs;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddGameTab extends Tab {

    // Create private instance variable.
    private static AddGameTab tab;

    private String imagePath;

    // Private constructor so AddGameTab is a singleton.
    private AddGameTab() {
        this.setText("Add Game");
        GameTable gameTable = new GameTable();
        DevInfoTable devInfoTable = new DevInfoTable();
        PlatformTable platformTable = new PlatformTable();
        CategoryTable categoryTable = new CategoryTable();

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Create game name input
        Text gameNameText = new Text("Game Name");
        root.add(gameNameText, 0, 0);
        TextField gameName = new TextField();
        root.add(gameName, 1, 0);

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

        // Create game developer input
        Text gameDeveloperText = new Text("Game Developer");
        root.add(gameDeveloperText, 0, 3);
        TextField gameDeveloper = new TextField();
        root.add(gameDeveloper, 1, 3);

        // Create game publisher input
        Text gamePublisherText = new Text("Game Publisher");
        root.add(gamePublisherText, 0, 4);
        TextField gamePublisher = new TextField();
        root.add(gamePublisher, 1, 4);

        // Create game platform input
        Text gamePlatformText = new Text("Game Platforms");
        root.add(gamePlatformText, 0, 5);
        ComboBox<Platform> gamePlatform = new ComboBox<>();
        gamePlatform.setItems(FXCollections.observableArrayList(platformTable.getAllPlatforms()));
        root.add(gamePlatform, 1, 5);

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

            Scene createPlatformScene = new Scene(createPlatformGrid, 400, 300);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Create Platform");
            newWindow.setScene(createPlatformScene);
            newWindow.show();

            addPlatform.setOnAction(event -> {
                Platform platform = new Platform(platformName.getText());
                platformTable.createPlatform(platform);
                newWindow.close();
            });
        });
        root.add(createPlatform, 2, 5);

        // Create game categories input
        Text gameCategoriesText = new Text("Game Categories");
        root.add(gameCategoriesText, 0, 6);
        ListView<Category> gameCategories = new ListView<>();
        gameCategories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        gameCategories.setItems(FXCollections.observableArrayList(categoryTable.getAllCategories()));
        root.add(gameCategories, 1, 6);

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
        root.add(createCategory, 2, 6);

        Button addGameButton = new Button("Add Game");
        addGameButton.setOnAction(event -> {
            Game game = new Game(
                    gameName.getText(),
                    imagePath,
                    gamePlatform.getSelectionModel().getSelectedItem().getId(),
                    gameReleaseYear.toString(),
                    new DevInfo(gameDeveloper.getText(), gamePublisher.getText()).getId()
            );
            gameTable.createGame(game);
        });
        root.add(addGameButton, 0, 7);

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
    public static AddGameTab getInstance() {
        if (tab == null) {
            tab = new AddGameTab();
        }
        return tab;
    }
}
