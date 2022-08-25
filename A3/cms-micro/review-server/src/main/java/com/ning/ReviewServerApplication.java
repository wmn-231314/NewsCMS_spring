package com.ning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.ning.mapper")
@EnableFeignClients
@EnableHystrix
public class ReviewServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewServerApplication.class, args);
    }

}
