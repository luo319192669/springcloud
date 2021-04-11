package com.luo.payment8002.service;

import com.luo.cloudapicommons.entity.Payment;

import java.util.List;


public interface IPaymentService {

    public int insertPayment(Payment payment);

    public List<Payment> selectAll();

    public Payment selectById(Long id);

}
