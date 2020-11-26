package tables;

import daos.CategoryDao;
import database.DBConst;
import database.Database;
import pojo.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryTable implements CategoryDao {
    //Connects to the data base
    Database database = Database.getInstance();
    //Creates a arrayList of categories
    ArrayList<Category> categories;

    /**
     *This function will grab all Categories form the database
     * @return And return Them
     */
    @Override
    public ArrayList<Category> getAllCategories() {
        String sql = "SELECT * FROM " + DBConst.TABLE_CATEGORY;
        categories = new ArrayList<Category>();
        try{
            Statement getCategory = database.getConnection().createStatement();
            ResultSet data = getCategory.executeQuery(sql);

            while (data.next()){
                categories.add(new Category(data.getInt(DBConst.CATEGORY_COLUMN_ID), data.getString(DBConst.CATEGORY_COLUMN_NAME)));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    /**
     *
     * @param id Using id users can search for a category by entering in a id and the function will compare the id's
     * @return returns a category or a null if there is not category
     */
    @Override
    public Category getCategory(int id) {
        String sql = "SELECT * FROM " + DBConst.TABLE_CATEGORY + " WHERE " + DBConst.CATEGORY_COLUMN_ID + " = " + id;
        Category category = null;

        try {
            Statement getCategory = database.getConnection().createStatement();
            ResultSet data = getCategory.executeQuery(sql);

            while (data.next()){
                category = new Category(data.getInt(DBConst.CATEGORY_COLUMN_ID), data.getString(DBConst.CATEGORY_COLUMN_NAME));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return category;
    }
}
