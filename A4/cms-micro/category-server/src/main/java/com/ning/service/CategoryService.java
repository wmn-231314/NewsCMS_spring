package com.ning.service;

import com.ning.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 通过id获取类别
     * @param id;
     * return Category
     */
    Category getCategoryById(int id) throws Exception;

    /**
     * 通过类别名获取类别
     * @param name
     * return Category
     */
    Category getCategoryByName(String name) throws Exception;

    /**
     * 获取全部类别
     * return List
     */
    List<Category> getAllCategory() throws Exception;

    /**
     * 添加类别
     * @param category
     * return int
     */
    int addCategory(Category category) throws Exception;

    /**
     * 通过id删除类别
     * @param id
     * return int
     */
    int deleteCategoryById(int id) throws Exception;

    /**
     * 通过列表删除类别
     * @param categoryList
     * return int
     */
    int deleteCategoryByList(List<Category> categoryList) throws Exception;

    /**
     * 更新类别信息
     * @param category
     * return int
     */
    int updateCategory(Category category) throws Exception;

    /**
     * 通过列表更新信息
     * @param categoryList
     * return int
     */
    int updateCategoryByList(List<Category> categoryList) throws Exception;
}
