package com.luo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZookeeper80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZookeeper80.class,args);
    }
}
