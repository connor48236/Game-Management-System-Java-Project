package tabs;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import main.GameTracker;

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

        GridPane root = new GridPane();

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

        root.add(imageButton, 0, 0);

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
