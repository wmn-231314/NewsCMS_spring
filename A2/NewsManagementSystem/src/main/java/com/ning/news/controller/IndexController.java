package com.ning.news.controller;

import com.ning.news.config.Config;
import com.ning.news.entity.Category;
import com.ning.news.entity.News;
import com.ning.news.exception.AccountException;
import com.ning.news.payload.request.LoginRequest;
import com.ning.news.payload.request.RegisterRequest;
import com.ning.news.payload.response.JWTBody;
import com.ning.news.payload.response.Response;
import com.ning.news.entity.User;
import com.ning.news.service.CategoryService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import com.ning.news.service.impl.UserDetailsImpl;
import com.ning.news.config.JwtUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class IndexController {
    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

    @Resource
    private NewsService newsService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    //所有新闻展示
    @GetMapping(value = {"/","index"}, name = "新闻展示")
    public Response index(){
        Map<String,Object> data = new HashMap<>();
        try{
            List<Category> categories = categoryService.getAllCategory();
            List<News> news = newsService.getAllNews();
            data.put("categories",categories);
            data.put("newsList",news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"News index success",data);
    }

    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public Response login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getAccount(), loginRequest.getPassword()));
        if(Objects.isNull(authentication)){
            return new Response(Config.ERROR_CODE,"wrong account or password",null);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        return new Response(Config.SUCCESS_CODE,"login success",new JWTBody(jwt,userDetails.getId(),userDetails.getName()
                ,userDetails.getUsername()
                ,role));
    }

    @PostMapping(value = "/register",produces = "application/json;charset=UTF-8")
    public Response register(@RequestBody RegisterRequest registerRequest) {
        try{
            User newUser = new User(registerRequest);
            User user = userService.register(newUser);
            if (user == null) {
                return new Response(Config.ERROR_CODE,"Invalid username or password.",null);
            }else {
                return new Response(Config.SUCCESS_CODE,"Register success",user);
            }
        } catch (AccountException e) {
            return new Response(Config.ERROR_CODE,"This account already exists.",null);
        } catch (Exception e) {
            return new Response(Config.ERROR_CODE,"Unknown error.",null);
        }
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response unauthorized() {
        return new Response(Config.UNAUTHORIZED_CODE, "Unauthorized", null);
    }
}
