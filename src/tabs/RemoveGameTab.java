package tabs;

import javafx.scene.control.Tab;

public class RemoveGameTab extends Tab {

    // Create private instance variable.
    private static RemoveGameTab tab;

    // Private constructor so GameLibraryTab is a singleton.
    private RemoveGameTab() {
        this.setText("Remove Game");
    }

    // Get instance method.
    public static RemoveGameTab getInstance() {
        if (tab == null) {
            tab = new RemoveGameTab();
        }
        return tab;
    }


}
