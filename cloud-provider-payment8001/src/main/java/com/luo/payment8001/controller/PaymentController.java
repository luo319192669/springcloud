package com.luo.payment8001.controller;

import com.luo.cloudapicommons.entity.CommonResult;
import com.luo.cloudapicommons.entity.Payment;
import com.luo.payment8001.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("Payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/selectAll")
    public CommonResult selectAll(){
        List<Payment> payments = paymentService.selectAll();
        CommonResult commonResult = new CommonResult(200, "查询全部数据成功！端口为："+serverPort, payments);
        log.info(commonResult.toString());
        log.info("本次运行服务器为:"+ serverPort);
        return  commonResult;
    }
    @GetMapping("/selectById/{id}")
    public CommonResult selectById(@PathVariable("id")Long id){
        Payment payment = paymentService.selectById(id);
        CommonResult commonResult = new CommonResult(200, "查询id为 "+id+" 的数据成功！端口: "+serverPort, payment);
        return  commonResult;
    }

    @PostMapping("/insertPayment")
    public CommonResult insertPayment(@RequestBody Payment payment){
        int i = paymentService.insertPayment(payment);
        CommonResult commonResult = new CommonResult(200, "添加成功",i );
        return commonResult;
    }

    @GetMapping("/getInfo")
    public List<ServiceInstance> getInfo(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("注册服务列表："+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务地址："+instance.getUri()+"-----服务IP："+instance.getHost()+"-----服务端口："+instance.getPort());
        }
        return instances;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeOut")
    public String getPaymentFeignTimeOut(){
        try {
            //暂停三秒钟
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }




}
