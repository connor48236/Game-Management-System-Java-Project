package tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import pojo.Category;
import pojo.Game;
import tables.CategoryTable;
import tables.DevInfoTable;
import tables.GameTable;
import tables.PlatformTable;

import java.io.IOException;
import java.util.ArrayList;

public class StatsTab extends Tab {

    private static StatsTab tab;
    private PieChart chart;

    private StatsTab() throws IOException {
        this.setText("Statistics");
        BorderPane pane = new BorderPane();
        chart = new PieChart();
        chart.setTitle("All Game Categories");
        chart.setVisible(true);
        generateChart();
        pane.setCenter(chart);

        // Set the borderpane as the content of the tab
        this.setContent(pane);
    }

    private void generateChart() throws IOException {

        GameTable gameTable = new GameTable();
        CategoryTable categoryTable = new CategoryTable();

        ArrayList<Category> categories = categoryTable.getAllCategories();

        chart.getData().clear();

        ArrayList<PieChart.Data> data = new ArrayList<>();
        for (Category category : categories) {
            if (gameTable.getCategoryCount(category.getId()) > 0) {
                data.add(new PieChart.Data(category.getName(), gameTable.getCategoryCount(category.getId())));
            }
        }

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(data);
        for (PieChart.Data d : data) {
            System.out.println("Pie chart data: " + d);
        }

        chart.setData(chartData);
    }

    public static StatsTab getInstance() throws IOException {
        if (tab == null) {
            tab = new StatsTab();
        }
        return tab;
    }

}
