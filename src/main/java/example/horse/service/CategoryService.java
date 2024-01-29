package example.horse.service;

import example.horse.pojo.Category;

import java.util.List;

/**
 * Created by LiuSheng at 2024/1/29 12:28
 */


public interface CategoryService {
    Integer addCategory(String category, String alias);

    List<Category> getAllCategoriesByUserId();

    Category getDetailById(Integer id);

    Integer update(Category category);

    Integer deleteById(Integer id);
}
