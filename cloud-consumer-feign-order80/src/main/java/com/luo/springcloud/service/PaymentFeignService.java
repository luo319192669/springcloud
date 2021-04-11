package com.luo.springcloud.service;

import com.luo.cloudapicommons.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "payment-service")
public interface PaymentFeignService {
    @GetMapping(value = "/Payment/selectById/{id}")
    CommonResult selectById(@PathVariable("id") Long id);


    @GetMapping(value = "/Payment/payment/feign/timeOut")
    public String getPaymentFeignTimeOut();
}
