package com.ning.news.mapper;

import com.ning.news.entity.Category;

import java.util.List;

public interface CategoryMapper {
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    List<Category> getAllCategory();

    int addCategory(Category category);

    int deleteCategoryById(int id);
    int deleteCategoryByList(List<Category> categoryList);

    int updateCategory(Category category);
    int updateCategoryByList(List<Category> categoryList);

}
