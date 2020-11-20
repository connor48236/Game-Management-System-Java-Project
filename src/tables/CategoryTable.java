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
    Database database = Database.getInstance();
    ArrayList<Category> categories;

    @Override
    public ArrayList<Category> getAllCategory() {
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
