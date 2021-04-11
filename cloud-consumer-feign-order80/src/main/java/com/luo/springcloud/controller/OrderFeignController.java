package com.luo.springcloud.controller;

import com.luo.cloudapicommons.entity.CommonResult;
import com.luo.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "consumer/Payment/selectById/{id}")
    public CommonResult selectById(@PathVariable("id") Long id){
        return paymentFeignService.selectById(id);
    }

    @GetMapping(value = "consumer/Payment/payment/feign/timeOut")
    public String getPaymentFeignTimeOut(){
    return  paymentFeignService.getPaymentFeignTimeOut();
    }
}
