package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Payment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ding
 * @date 2021-1-1
 *
 * order服务通过restTemPlate去调用payment8001服务新增payment
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
@Api(tags = "订单")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 指定服务提供者；可能是集群多个服务
     */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @PostMapping("/consumers")
    @ApiOperation("新增")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    public Integer create(@ApiParam("对象") @RequestBody Payment payment) {
        log.info("新增---调用服务提供者");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/payments", payment, Integer.class);
    }

    @GetMapping("/consumers/{id}")
    @ApiOperation("查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    public Payment getPayment(@PathVariable("id") Long id) {
        log.info("查询--调用服务提供者");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/payments/" + id, Payment.class);
    }
}
