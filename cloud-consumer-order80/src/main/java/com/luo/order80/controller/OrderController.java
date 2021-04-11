package com.luo.order80.controller;

import com.luo.cloudapicommons.entity.CommonResult;
import com.luo.cloudapicommons.entity.Payment;
import com.luo.order80.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("consumer/Payment/insertPayment")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/Payment/insertPayment",payment,CommonResult.class);
    }


    @GetMapping("consumer/Payment/selectAll")
    public CommonResult<List<Payment>> selectAll(){
        return restTemplate.getForObject(PAYMENT_URL+"/Payment/selectAll",CommonResult.class);
    }

    @GetMapping("consumer/Payment/selectById/{id}")
    public CommonResult<Payment> selectById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/Payment/selectById/"+id,CommonResult.class);
    }

    @GetMapping("consumer/Payment/getInfo")
    public Object getServerInfo(){
        log.info("服务信息："+restTemplate.getForObject(PAYMENT_URL+"/Payment/getInfo", Object.class));
        return restTemplate.getForObject(PAYMENT_URL+"/Payment/getInfo", Object.class).toString();
    }


    @GetMapping("consumer/Payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/Payment/selectById/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<Payment>(444,"操作失败！");
        }
    }

    @GetMapping("consumer/Payment/forEntity/create")
    public CommonResult<Payment> createForEntity(Payment payment){
        return restTemplate.postForEntity(PAYMENT_URL+"/Payment/insertPayment",payment,CommonResult.class).getBody();
    }

    @GetMapping(value = "consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("PAYMENT-SERVICE");
        if (serviceInstances == null || serviceInstances.size()<=0){
            return null;
        }
        ServiceInstance instances = loadBalancer.instances(serviceInstances);
        URI uri = instances.getUri();
        return restTemplate.getForObject(uri+"Payment/payment/lb",String.class);

    }






}
