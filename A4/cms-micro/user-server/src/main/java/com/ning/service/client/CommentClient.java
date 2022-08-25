package com.ning.service.client;

import com.ning.payload.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("commentserver")
public interface CommentClient {
    @PostMapping("/news-micro/comment/deleteByUser/{id}")
    Response deleteByUserId(@PathVariable int id);
}
