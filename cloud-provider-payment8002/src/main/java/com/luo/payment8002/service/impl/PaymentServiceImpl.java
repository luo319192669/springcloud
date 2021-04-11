package com.luo.payment8002.service.impl;

import com.luo.cloudapicommons.entity.Payment;
import com.luo.payment8002.mapper.PaymentMapper;
import com.luo.payment8002.service.IPaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentServiceImpl  implements IPaymentService{

    @Autowired
    private PaymentMapper paymentMapper;

    public int insertPayment(Payment payment){
        return paymentMapper.insertPayment(payment);
    }

    public List<Payment> selectAll(){
        return paymentMapper.selectAll();
    }

    public Payment selectById(@Param("id") Long id){
        return paymentMapper.selectById(id);
    }
}
