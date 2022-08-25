package com.ning.news.service.impl;

import com.ning.news.config.Config;
import com.ning.news.entity.Category;
import com.ning.news.entity.News;
import com.ning.news.exception.CategoryException;
import com.ning.news.mapper.CategoryMapper;
import com.ning.news.mapper.NewsMapper;
import com.ning.news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    NewsMapper newsMapper;

    @Override
    public Category getCategoryById(int id) throws Exception {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) throws Exception {
        return categoryMapper.getCategoryByName(name);
    }

    @Override
    public List<Category> getAllCategory() throws Exception {
        List<Category> categoryList = categoryMapper.getAllCategory();
        return categoryList;
    }

    @Override
    public int addCategory(Category category) throws Exception {
        Category testSame = categoryMapper.getCategoryByName(category.getCategoryName());
        if(testSame != null){
            throw new CategoryException("类别名不能相同");
        }else{
            return categoryMapper.addCategory(category);
        }
    }

    @Override
    public int deleteCategoryById(int id) throws Exception {
        List<News> newsList = newsMapper.getNewsByCategory(id);
        for (News news:newsList){
            news.setCategoryId(Config.CATEGORY_ORIGIN);
            newsMapper.updateNews(news);
        }
        return categoryMapper.deleteCategoryById(id);
    }

    @Override
    public int deleteCategoryByList(List<Category> categoryList) throws Exception {
        return categoryMapper.deleteCategoryByList(categoryList);
    }

    @Override
    public int updateCategory(Category category) throws Exception {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int updateCategoryByList(List<Category> categoryList) throws Exception {
        return categoryMapper.updateCategoryByList(categoryList);
    }
}
