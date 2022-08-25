package com.ning.service.impl;

import com.ning.payload.response.Response;
import com.ning.service.client.NewsClient;
import com.ning.utils.Config;
import com.ning.entity.Category;
import com.ning.entity.News;
//import com.ning.exception.CategoryException;
import com.ning.mapper.CategoryMapper;
//import com.ning.mapper.NewsMapper;
import com.ning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Resource
    NewsClient newsClient;

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
            return 0;
        }
        return categoryMapper.addCategory(category);
    }

    @Override
    public int deleteCategoryById(int id) throws Exception {
        List<News> newsList = newsClient.getNewsByCategory(id);
        for (News news:newsList){
            news.setCategoryId(Config.CATEGORY_ORIGIN);
            newsClient.updateNewsStatus(news);
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
