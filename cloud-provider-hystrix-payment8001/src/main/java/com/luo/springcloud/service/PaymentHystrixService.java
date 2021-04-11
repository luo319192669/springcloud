package com.luo.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {


    //---服务降级

    public String paymentInfOk(Integer id){
        return "线程池"+Thread.currentThread().getName()+"   paymentInfoOk,id:  "+id+"\t"+"哈哈O(∩_∩)O";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler"
            ,commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfoOutTime(Integer id){
//        int timeNumber = 5;
//        int i = 10/0;
        try { TimeUnit.MILLISECONDS.sleep(3000 ); }catch (InterruptedException e){ e.printStackTrace(); }
        return "线程池"+Thread.currentThread().getName()+"   paymentInfoTimeOut,id:  "+id+"\t"+"呜呜┭┮﹏┭┮耗时秒钟！";
    }

    public String paymentInfoTimeOutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"   paymentInfoTimeOutHandler,id:  "+id+"\t"+"呜呜┭┮﹏┭┮发生错误，请稍后重试！";
    }

    //--服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id<0){
            throw new RuntimeException("*********id不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id不能为负数，请稍后重试，┭┮﹏┭┮---- id:"+id;
    }
}
