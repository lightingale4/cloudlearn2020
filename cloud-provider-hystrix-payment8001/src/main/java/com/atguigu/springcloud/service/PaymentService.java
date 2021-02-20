package com.atguigu.springcloud.service;

/**
 * @author ding
 */
public interface PaymentService {

    String paymentInfo_ok(Integer id);

    String paymentInfo_Timeout(Integer id);
}
