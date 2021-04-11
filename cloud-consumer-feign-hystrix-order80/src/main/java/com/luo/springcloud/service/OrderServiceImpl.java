package com.luo.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements IOrderService {
    public String paymentInfoOk(Integer id) {
        return "-------OrderServiceImpl paymentInfoOk";
    }

    public String paymentInfoTimeOut(Integer id) {
        return "-------OrderServiceImpl paymentInfoTimeOut";
    }
}
