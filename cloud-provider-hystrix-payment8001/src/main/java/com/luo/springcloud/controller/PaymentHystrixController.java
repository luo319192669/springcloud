package com.luo.springcloud.controller;

import com.luo.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfOk(id);
        return result;
    }

    @GetMapping("payment/hystrix/timeOut/{id}")
    public String paymentInfoTimeOut(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfoOutTime(id);
        return result;
    }

    //---服务熔断

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info(result);
        return result;
    }

}
