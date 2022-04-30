package com.sise.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sise"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.sise.staservice.mapper")
public class StaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
