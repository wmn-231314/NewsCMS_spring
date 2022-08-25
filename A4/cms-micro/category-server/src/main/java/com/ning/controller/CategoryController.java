package com.ning.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ning.entity.Category;
import com.ning.payload.response.Response;
import com.ning.service.CategoryService;
import com.ning.utils.Config;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping(value = "/getCategoryInfo")
    @ResponseBody
    public Response getCategoryInfo() throws Exception {
        List<Category> categoryList = categoryService.getAllCategory();
        return new Response(Config.SUCCESS_CODE,"success",categoryList);
    }

    @GetMapping("/delete/category")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "onError")
    public Response deleteCategory(@RequestParam int id){
        if (id == Config.CATEGORY_ORIGIN){
            return new Response(Config.ERROR_CODE,"origin",null);
        }
        try {
            int delete = categoryService.deleteCategoryById(id);
            if(delete > 0){
                return new Response(Config.SUCCESS_CODE,"success",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.ERROR_CODE,"fail",null);
    }

    @GetMapping(value = {"/getCategory"}, name = "获取分类")
    public Response getAllCategory(){
        Map<String,Object> data = new HashMap<>();
        try{
            List<Category> categories = categoryService.getAllCategory();
            data.put("categories",categories);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"get category success",data);
    }

    public Response onError(int id) {
        return new Response(Config.ERROR_CODE,"other micro server die",null);
    }
}
