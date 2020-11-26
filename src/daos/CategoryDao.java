package daos;

import pojo.Category;

import java.util.ArrayList;

public interface CategoryDao {
    public ArrayList<Category> getAllCategories();
    public Category getCategory(int id);
}
