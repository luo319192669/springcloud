package com.luo.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ConsumerHystrixDashBoardMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixDashBoardMain.class,args);
    }
}
