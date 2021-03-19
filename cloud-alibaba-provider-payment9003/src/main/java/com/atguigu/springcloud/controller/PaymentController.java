package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ding
 */
@RestController
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;


    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    /**
     * 模拟Dao层
     */
    static {
        hashMap.put(1L, new Payment(1L, "11222gredd3we34y"));
        hashMap.put(2L, new Payment(2L, "11332gredd3we34r"));
        hashMap.put(3L, new Payment(3L, "11245gredd3we345"));
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult commonResult = new CommonResult(200, "from mysql,serverPort: " + serverPort, payment);
        return commonResult;
    }
}
