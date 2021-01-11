package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * spring官方提供的服务发现的restapi
     * @author ding
     */
    @Resource
    private DiscoveryClient discoveryClient;

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

    @ApiOperation("当前微服务中-服务发现")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @GetMapping("payments/getServices")
    public Object getServices() {
        /**
         * 获取有多少个service
         */
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("----" + service);

        }
        /**
         * 获取该服务下有多少个实例
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }
}
