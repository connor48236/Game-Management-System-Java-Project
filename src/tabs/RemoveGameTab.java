package tabs;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import pojo.Game;
import tables.GameTable;

import javax.swing.border.Border;

public class RemoveGameTab extends Tab {

    // Create private instance variable.
    private static RemoveGameTab tab;

    // Private constructor so GameLibraryTab is a singleton.
    private RemoveGameTab() {
        this.setText("Remove Game");

        //add game table to class
        GameTable gameTable = new GameTable();

        //setting up pane
        BorderPane root = new BorderPane();

        //Adding a game picker
        Text pickAGame = new Text("Pick a game to delete");
        root.setTop(pickAGame);

        //add the list for games to pick from
        ComboBox<Game> gameComboBox = new ComboBox<>();
        gameComboBox.setItems(FXCollections.observableList(gameTable.getAllGames()));
        root.setCenter(gameComboBox);

        //add The delete Button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->{
            Game game = (Game) gameComboBox.getSelectionModel().getSelectedItem();
            gameTable.deleteGame(game.getId());
            refreshGameBox();
        });

        //adding the button to root
        root.setBottom(deleteButton);
        this.setContent(root);

    }

    //The function to refresh the list of games
    public void refreshGameBox(){
        ComboBox gameComboBox = new ComboBox();
        gameComboBox.getItems().clear();
        gameComboBox.getItems().addAll(gameComboBox.getItems());
    }

    // Get instance method.
    public static RemoveGameTab getInstance() {
        if (tab == null) {
            tab = new RemoveGameTab();
        }
        return tab;
    }


}
