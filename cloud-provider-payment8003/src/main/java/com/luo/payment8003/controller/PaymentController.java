package com.luo.payment8003.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentZookeeper(){
        log.info("serverPort=========="+serverPort);
        return "spring cloud serverPort:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
