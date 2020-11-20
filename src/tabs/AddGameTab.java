package tabs;

import javafx.scene.control.Tab;

public class AddGameTab extends Tab {

    // Create private instance variable.
    private static AddGameTab tab;

    // Private constructor so AddGameTab is a singleton.
    private AddGameTab() {
        this.setText("Add Game");



    }

    // Get instance method.
    public static AddGameTab getInstance() {
        if (tab == null) {
            tab = new AddGameTab();
        }
        return tab;
    }
}
