package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author ding
 * 该类主要去实现hystrix的全局异常的处理
 */
@Component
public class PaymentFallBackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "paymentInfo_ok方法异常";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "paymentInfo_Timeout方法异常";
    }
}
