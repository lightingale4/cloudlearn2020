package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @author ding
 */
@RestController
@Slf4j
public class CircleBreakerController {


    public static final String SERVICE_URL = "http://nacos-payment-provider";


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback") //只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //只负责sentinel配置的异常
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {

        CommonResult commonResult = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        } else if (commonResult.getData() == null) {
            throw new NullPointerException("空指针异常，没对应记录");
        }
        return commonResult;
    }

    //handlerFallback
    public CommonResult handlerFallback(@PathVariable Long id, Throwable err) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底异常" + err.getMessage(), payment);

    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException err) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "兜底异常" + err.getMessage(), payment);

    }


    @GetMapping(value = "/consumer/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        CommonResult<Payment> paymentCommonResult = paymentService.paymentSql(id);
        return paymentCommonResult;
    }
}
