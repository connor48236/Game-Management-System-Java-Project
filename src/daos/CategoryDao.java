package daos;

import pojo.Category;

import java.util.ArrayList;

/**
 * @author Connor Cozad
 */

public interface CategoryDao {
    public ArrayList<Category> getAllCategories();
    public Category getCategory(int id);
    public void createCategory(Category category);
}
