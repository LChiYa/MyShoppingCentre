package com.hsd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.hsd.mapper"})
public class HsdOrdersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsdOrdersServiceApplication.class, args);
    }

}
