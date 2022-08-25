package com.ning.service.client;

import com.ning.payload.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("commentserver")
public interface CommentClient {
    @PostMapping({"/news-micro/comment/deleteByNews/{id}"})
    Response deleteByNews(@PathVariable int id);
}
