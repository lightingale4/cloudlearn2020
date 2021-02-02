package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ding
 * @Date 2021-23:40
 */
@RestController
@Slf4j
public class FeignController {

    private static final Logger logger = LoggerFactory.getLogger(FeignController.class);

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("consumer/payment/payments/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> payment = paymentFeignService.getPayment(id);
        return payment;
    }
}
