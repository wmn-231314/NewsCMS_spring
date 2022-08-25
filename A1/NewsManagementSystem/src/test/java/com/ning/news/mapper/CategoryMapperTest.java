package com.ning.news.mapper;

import com.ning.news.BaseTest;
import com.ning.news.entity.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryMapperTest extends BaseTest {
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void getCategoryById() {
        int id = 1;
        Category category = categoryMapper.getCategoryById(id);
        System.out.println("get by id");
        System.out.println(category);
    }

    @Test
    public void getCategoryByName() {
        String name = "测试类别";
        Category category = categoryMapper.getCategoryByName(name);
        System.out.println("get by name"+category);
    }

    @Test
    public void getAllCategory() {
        List<Category> categoryList = categoryMapper.getAllCategory();
        System.out.println("get all");
        for(Category category:categoryList){
            System.out.println(category);
        }
    }

    @Test
    public void addCategory() {
        Category category = new Category("测试类别2");
        categoryMapper.addCategory(category);
        System.out.println("add");
        getAllCategory();
    }

    @Test
    public void deleteCategoryById() {
        int id = 10;
        categoryMapper.deleteCategoryById(id);
        System.out.println("delete id"+categoryMapper.getCategoryById(id));
        getAllCategory();
    }

    @Test
    public void deleteCategoryByList() {
        List<Category> categoryList = categoryMapper.getAllCategory();
        int len = categoryList.size()-2;
        for(int i = 0; i < len; i++){
            categoryList.remove(0);
        }
        categoryMapper.deleteCategoryByList(categoryList);
        System.out.println("delete list");
        getAllCategory();
    }

    @Test
    public void updateCategory() {
        Category category = categoryMapper.getCategoryById(1);
        category.setCategoryName(category.getCategoryName()+"update");
        categoryMapper.updateCategory(category);
        System.out.println("update"+categoryMapper.getCategoryById(1));
    }

    @Test
    public void updateCategoryByList() {
        List<Category> categoryList = categoryMapper.getAllCategory();
        int len = categoryList.size()-2;
        for(int i = 0; i < len; i++){
            categoryList.remove(0);
        }
        for(Category category:categoryList){
            category.setCategoryName(category.getCategoryName()+"list");
        }
        categoryMapper.updateCategoryByList(categoryList);
        System.out.println("update list");
        getAllCategory();
    }
}