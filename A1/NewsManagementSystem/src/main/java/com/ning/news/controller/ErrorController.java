package com.ning.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/403")
    public String error403(){
        return "error/403";
    }

    //所有新闻展示
    @RequestMapping("/404")
    public String error404(){
        return "error/404";
    }

    //所有新闻展示
    @RequestMapping("/500")
    public String error500(){
        return "error/500";
    }
}
