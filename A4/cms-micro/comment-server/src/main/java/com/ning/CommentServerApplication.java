package com.ning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.ning.mapper")
@EnableFeignClients
public class CommentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentServerApplication.class, args);
    }

}
