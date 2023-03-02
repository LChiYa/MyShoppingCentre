package com.hsd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"com.hsd.mapper"})
public class HsdGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsdGoodsServiceApplication.class, args);
    }

}
