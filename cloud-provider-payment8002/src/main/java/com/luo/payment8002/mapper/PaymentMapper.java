package com.luo.payment8002.mapper;

import com.luo.cloudapicommons.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PaymentMapper {

    public int insertPayment(Payment payment);

    public List<Payment> selectAll();

    public Payment selectById(@Param("id") Long id);

}
