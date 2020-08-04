package com.huangjiang.taskcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.**.mapper")
@SpringBootApplication
public class TaskCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskCenterApplication.class, args);
    }

}
