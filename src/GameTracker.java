import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tabs.AddGameTab;
import tabs.GameLibraryTab;
import tabs.RemoveGameTab;

public class GameTracker extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Create menu bar
        MenuBar menuBar = new MenuBar();

        // Create menu options
        Menu fileMenu = new Menu("File");
        Menu windowMenu = new Menu("Window");

        // Build menu items
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            System.exit(0);
        });

        // Add menu items to menu options
        fileMenu.getItems().add(exit);

        // Add menu options to menu bar
        menuBar.getMenus().addAll(fileMenu, windowMenu);

        // Create tab pane
        TabPane tabPane = new TabPane();

        // Add tabs to tab pane
        GameLibraryTab gameLibraryTab = GameLibraryTab.getInstance();
        AddGameTab addGameTab = AddGameTab.getInstance();
        RemoveGameTab removeGameTab = RemoveGameTab.getInstance();

        tabPane.getTabs().addAll(gameLibraryTab, addGameTab, removeGameTab);
        tabPane.setSide(Side.LEFT);

        // Create border pane
        BorderPane root = new BorderPane();

        // Add the menu bar
        root.setTop(menuBar);

        // Add the tab pane
        root.setCenter(tabPane);


        // Scene and stage setup
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Game Management System");
        stage.show();

    }
}
