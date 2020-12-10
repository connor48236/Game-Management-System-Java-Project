package tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import pojo.Category;
import pojo.Platform;
import tables.CategoryTable;
import tables.GameTable;
import tables.PlatformTable;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Chris Corbett
 * stats tab to display the stats from in
 * the database
 */
public class StatsTab extends Tab {

    private static StatsTab tab;
    private PieChart categoryChart;
    private PieChart platformChart;

    private StatsTab() throws IOException {
        this.setText("Statistics");
        BorderPane pane = new BorderPane();

        // Create the category chart
        categoryChart = new PieChart();
        categoryChart.setTitle("All Game Categories");
        categoryChart.setVisible(true);
        generateCategoryChart();
        pane.setLeft(categoryChart);

        // Create the platform chart
        platformChart = new PieChart();
        platformChart.setTitle("All Game Platforms");
        categoryChart.setVisible(true);
        generatePlatformChart();
        pane.setCenter(platformChart);

        // Set the borderpane as the content of the tab
        this.setContent(pane);
    }

    /**
     * @author Chris Corbett
     * generates the chart for all the
     * platform info
     * @throws IOException
     */
    public void generatePlatformChart() throws IOException {
        GameTable gameTable = new GameTable();
        PlatformTable platformTable = new PlatformTable();

        ArrayList<Platform> platforms = platformTable.getAllPlatforms();

        platformChart.getData().clear();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Platform platform : platforms) {
            if (gameTable.getPlatformCount(platform.getId()) > 0) {
                data.add(new PieChart.Data(platform.getName(), gameTable.getPlatformCount(platform.getId())));
            }
        }

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(data);

        platformChart.setData(chartData);
    }

    /**
     * @author Chris Corbett
     * generates the chart for all the
     * category info
     * @throws IOException
     */
    public void generateCategoryChart() throws IOException {

        GameTable gameTable = new GameTable();
        CategoryTable categoryTable = new CategoryTable();

        ArrayList<Category> categories = categoryTable.getAllCategories();

        categoryChart.getData().clear();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Category category : categories) {
            if (gameTable.getCategoryCount(category.getId()) > 0) {
                data.add(new PieChart.Data(category.getName(), gameTable.getCategoryCount(category.getId())));
            }
        }

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(data);

        categoryChart.setData(chartData);
    }

    public static StatsTab getInstance() throws IOException {
        if (tab == null) {
            tab = new StatsTab();
        }
        return tab;
    }

}
