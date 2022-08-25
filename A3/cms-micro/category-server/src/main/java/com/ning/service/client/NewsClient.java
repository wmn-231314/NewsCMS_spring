package com.ning.service.client;

import com.ning.entity.News;
import com.ning.payload.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("newsserver")
public interface NewsClient {
    @GetMapping("/getByCategory/{category_id}")
    List<News> getNewsByCategory(@PathVariable("category_id") Integer category_id);

    @PostMapping("/news-micro/news/updateStatus")
    Response updateNewsStatus(@RequestParam News news);
}
