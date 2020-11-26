package tabs;

import javafx.scene.control.Tab;

public class GameLibraryTab extends Tab {

    // Create private instance variable.
    private static GameLibraryTab tab;

    // Private constructor so GameLibraryTab is a singleton.
    private GameLibraryTab() {
        this.setText("Game Library");



    }



    // Get instance method.
    public static GameLibraryTab getInstance() {
        if (tab == null) {
            tab = new GameLibraryTab();
        }
        return tab;
    }
}
