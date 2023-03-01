package com.hsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HsdEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsdEurekaServiceApplication.class, args);
    }

}
