package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.loadBalance.MyLoadBalance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author ding
 * @date 2021-1-1
 * <p>
 * order服务通过restTemPlate去调用payment8001服务新增payment
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
@Api(tags = "订单")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLoadBalance myLoadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

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

    /**
     * getForObject方法返回响应体中数据转化成的对象，可以理解为json
     *
     * @param id
     * @return
     */
    @GetMapping("/consumers/{id}")
    @ApiOperation("查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    public Payment getPayment(@PathVariable("id") Long id) {
        log.info("查询--调用服务提供者");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/payments/" + id, Payment.class);
    }

    /**
     * 调用REStTempalte的getForEntity,返回对象为ResponseEntity,包含了一些响应的信息，响应头，状态码等
     *
     * @param id
     * @return
     */
    @GetMapping("/consumers/payment/getForEntity/{id}")
    @ApiOperation("查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        Assert.notNull(id, "id不能为空");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/payments/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping("/consumer/payment/lb")
    @ApiOperation("查询服务列表")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    public String getServices() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = myLoadBalance.getServiceInstance(instances);
        URI uri = serviceInstance.getUri();
        String resultObject = restTemplate.getForObject(uri + "/payment/payment/lb", String.class);
        return resultObject;
    }

    /**
     * zipkin+sleuth 链路追踪
     * @return
     */
    @ApiOperation("链路调用")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){

        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/payment/zipkin", String.class);
        return result;
    }
}
