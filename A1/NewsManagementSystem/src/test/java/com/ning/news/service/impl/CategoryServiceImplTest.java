package com.ning.news.service.impl;

import com.ning.news.BaseTest;
import com.ning.news.entity.Category;
import com.ning.news.exception.CategoryException;
import com.ning.news.service.CategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceImplTest extends BaseTest {
    @Autowired
    CategoryService categoryService;

    @Test
    public void getCategoryById() {
        int id = 1;
        try {
            Category category = categoryService.getCategoryById(id);
            System.out.println("get id "+category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCategoryByName() {
        String name = "游戏";
        try {
            Category category = categoryService.getCategoryByName(name);
            System.out.println("get name "+category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllCategory() {
        try {
            List<Category> categoryList = categoryService.getAllCategory();
            System.out.println("get all");
            for(Category category:categoryList){
                System.out.println(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addCategory() {
        Category category = new Category("测试添加");
        try {
            int res = categoryService.addCategory(category);
            System.out.println("add "+res);
            res = categoryService.addCategory(category);
        } catch (CategoryException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCategoryById() {
        int id = 1;
        try {
            int res = categoryService.deleteCategoryById(id);
            System.out.println("delete id "+res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteCategoryByList() {
        try {
            List<Category> categoryList = categoryService.getAllCategory();
            int len = categoryList.size()-2;
            for(int i = 0; i < len; i++){
                categoryList.remove(0);
            }
            int res = categoryService.deleteCategoryByList(categoryList);
            System.out.println("delete list "+res);
            getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCategory() {
        try {
            Category category = categoryService.getCategoryById(2);
            category.setCategoryName(category.getCategoryName()+"up");
            int res = categoryService.updateCategory(category);
            System.out.println("update "+res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateCategoryByList() {
        try {
            List<Category> categoryList = categoryService.getAllCategory();
            int len = categoryList.size()-2;
            for(int i = 0; i < len; i++){
                categoryList.remove(0);
            }
            for(Category category:categoryList){
                category.setCategoryName(category.getCategoryName()+"list");
            }
            categoryService.updateCategoryByList(categoryList);
            System.out.println("update list");
            getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}