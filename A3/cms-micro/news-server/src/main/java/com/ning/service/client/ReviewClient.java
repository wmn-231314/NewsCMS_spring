package com.ning.service.client;

import com.ning.payload.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("reviewserver")
public interface ReviewClient {
    @PostMapping({"/news-micro/review/deleteReview/{id}"})
    Response deleteNewsReviewById(@PathVariable int id);
}
