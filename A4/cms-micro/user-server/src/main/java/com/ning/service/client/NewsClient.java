package com.ning.service.client;

import com.ning.payload.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("newsserver")
public interface NewsClient {
    @PostMapping("/news-micro/news/deleteNewsByProviderId/{id}")
    public Response deleteNewsByProviderId(@PathVariable int id);
}
