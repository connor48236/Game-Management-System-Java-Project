package tabs;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pojo.Game;
import tables.GameTable;

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


        //Sets gameInfo to a game based on ID
        Game gameInfo = gameTable.getGame(page);
        //Adds The borderPane
        BorderPane root = new BorderPane();

        //Will Only show if there are games in the dataBase
        if (!gameTable.getAllGames().isEmpty()) {

            if (gameInfo.getImage() == null) {
                //Will set the text to No Image Provided
                Text noImage = new Text("No Image Provided");
                //Sets the image to center textAlignment
                noImage.setTextAlignment(TextAlignment.CENTER);
                //Will set the text to the center of the root
                root.setCenter(noImage);
            } else{
                //Sets gameImage to the game Image of that id
                ImageView gameImage = new ImageView(gameInfo.getImage());
                //sets the game Image to center
                root.setCenter(gameImage);
        }

            //Sets gameName to the game name based on id
            Text gameName = new Text(gameInfo.getName());
            //This will set the text to center
            gameName.setTextAlignment(TextAlignment.CENTER);
            //This will set the gameName to the bottom of the root
            root.setBottom(gameName);

            //Setting up the nextGame Button
            Button nextGame = new Button("Next Game");
            //OnClick of this Button it will add 1 to page going to the next game
            //If page = the size of all the game "The end" it will set the page to zero
            nextGame.setOnAction(e -> {
                if (page == gameTable.getAllGames().size()) {
                    page = 0;
                } else {
                    page++;
                }
            });
            //This will set the button to the right
            root.setRight(nextGame);

            //This sets up the previousGame button
            Button previousGame = new Button("Previous Game");
            //On click of this button it will go back a game unless it is at 0
            //Then it will set the page to the end of the list
            previousGame.setOnAction(e -> {
                if (page == 0) {
                    page = gameTable.getAllGames().size();
                } else {
                    page--;
                }
            });
            //This will set the button to the left side
            root.setLeft(previousGame);

            //Will show if there are no games
        }else {
            //This sets the text
            Text pleaseInsertGame = new Text("Please Insert A Game To View");
            //This will set the text to center
            pleaseInsertGame.setTextAlignment(TextAlignment.CENTER);
            //This will set the text to the center of root
            root.setCenter(pleaseInsertGame);
        }

        //This sets the content to root "BorderPane"
        this.setContent(root);

    }



    // Get instance method.
    public static GameLibraryTab getInstance() {
        if (tab == null) {
            tab = new GameLibraryTab();
        }
        return tab;
    }
}
