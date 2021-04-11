package com.luo.springcloud.controller;


import com.luo.springcloud.service.IOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderFeignHystrixController {

    @Autowired
    private IOrderService orderService;
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id")Integer id){
        String result = orderService.paymentInfoOk(id);
        return result;
    }

//    @HystrixCommand(fallbackMethod = "paymentTimeOutFullBackMethod"
//            ,commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    @HystrixCommand
    public String paymentInfoOutTime(Integer id){
        int i = 10/0;
        String result = orderService.paymentInfoTimeOut(id);
        return result;
    }

    public String paymentTimeOutFullBackMethod(Integer id){
        return "我是消费者80，服务出错呜呜┭┮﹏┭┮发生错误，请稍后重试！";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常信息处理，请稍后重试，、┭┮﹏┭┮";
    }

}
