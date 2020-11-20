package daos;

import pojo.Category;

import java.util.ArrayList;

public interface CategoryDao {
    public ArrayList<Category> getAllCategory();
    public Category getCategory(int id);
}
