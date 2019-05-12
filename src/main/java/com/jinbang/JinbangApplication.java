package com.jinbang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jinbang.mapper")
public class JinbangApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinbangApplication.class, args);
    }

}
