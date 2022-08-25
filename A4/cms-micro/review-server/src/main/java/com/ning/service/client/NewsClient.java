package com.ning.service.client;

import com.ning.entity.News;
import com.ning.payload.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("newsserver")
public interface NewsClient {

    @PostMapping("/news-micro/news/updateNews")
    Response updateNews(@RequestParam News news);

    @PostMapping("/news-micro/news/updateStatus")
    Response updateNewsStatus(@RequestParam News news);
}
