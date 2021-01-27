package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ding
 */
@RestController
@RequestMapping("/payment")
@Api(tags = "支付")
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @ApiOperation("新增")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @PostMapping("payments")
    public Integer create(@ApiParam("对象") @RequestBody Payment payment) {
        log.info("新增");
        int i = paymentService.create(payment);
        System.out.println("server端口：" + serverPort);
        return i;
    }

    @ApiOperation("通过id查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @GetMapping("payments/{id}")
    public Payment getById(@ApiParam("传入id") @PathVariable("id") Long id) {
        log.info("查询");
        System.out.println("server端口：" + serverPort);
        return paymentService.getOneById(id);
    }

    @ApiOperation("获取")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
}
