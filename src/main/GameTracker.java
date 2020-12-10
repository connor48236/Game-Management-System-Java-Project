package main;

import com.mysql.cj.protocol.Message;
import database.Database;
import database.GetLogin;
import database.Login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tabs.AddGameTab;
import tabs.GameLibraryTab;
import tabs.RemoveGameTab;
import tabs.StatsTab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class GameTracker extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        startApplication(stage);
    }


    private static void startApplication(Stage stage) throws IOException {
        getLoginInfo();

        File loginFile = new File("src/login/login.txt");
        if (loginFile.isFile()) {

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
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            // Add tabs to tab pane
            GameLibraryTab gameLibraryTab = GameLibraryTab.getInstance();
            AddGameTab addGameTab = AddGameTab.getInstance();
            RemoveGameTab removeGameTab = RemoveGameTab.getInstance();
            StatsTab statsTab = StatsTab.getInstance();

            tabPane.getTabs().addAll(gameLibraryTab, addGameTab, removeGameTab, statsTab);
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

            GameTracker.stage = stage;

            GetLogin.getLoginInfo();

            Database database = Database.getInstance();
        }
    }

    /**
     * @author Chris Corbett
     * restarts the application
     * @param stage
     * @throws IOException
     */
    private static void restart(Stage stage) throws IOException {
        startApplication(stage);
    }

    /**
     * @author Chris Corbett
     * Checks if the user has logged in and if not
     * prompts the user to login.
     */
    public static void getLoginInfo() {
        File loginFile = new File("src/login/login.txt");
        if (!loginFile.isFile()) {

            // Create grid pane for new window
            GridPane createLoginInfo = new GridPane();
            createLoginInfo.setHgap(10);
            createLoginInfo.setVgap(10);
            createLoginInfo.setPadding(new Insets(10, 10, 10, 10));

            // Create username input.
            Text userNameText = new Text("Username");
            createLoginInfo.add(userNameText, 0, 0);
            TextField userName = new TextField();
            createLoginInfo.add(userName, 1, 0);

            // Create database input
            Text userDatabaseText = new Text("Database");
            createLoginInfo.add(userDatabaseText, 0, 1);
            TextField userDatabase = new TextField();
            createLoginInfo.add(userDatabase, 1, 1);

            // Create password input
            Text userPasswordText = new Text("Password");
            createLoginInfo.add(userPasswordText, 0, 2);
            TextField userPassword = new TextField();
            createLoginInfo.add(userPassword, 1, 2);

            // Create database location input
            Text userDatabaseLocationText = new Text("Database Location");
            createLoginInfo.add(userDatabaseLocationText, 0, 3);
            TextField userDatabaseLocation = new TextField();
            createLoginInfo.add(userDatabaseLocation, 1, 3);

            // Create finish button
            Button finishButton = new Button("Finish");
            createLoginInfo.add(finishButton, 0, 4);

            Scene createPlatformScene = new Scene(createLoginInfo, 400, 300);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Database Login Information");
            newWindow.setScene(createPlatformScene);
            newWindow.show();

            finishButton.setOnAction(e -> {
                String[] userLoginInfo = {userDatabase.getText(), userName.getText(), userPassword.getText(), userDatabaseLocation.getText()};
                String login = String.join(",", userLoginInfo);

                try {
                    loginFile.getParentFile().mkdirs();
                    loginFile.createNewFile();
                    FileOutputStream oFile = new FileOutputStream(loginFile);

                    byte[] loginBytes = login.getBytes();

                    oFile.write(loginBytes);
                    oFile.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                newWindow.close();

                try {
                    restart(new Stage());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }

    public static Stage getStage() {
        return stage;
    }
}
