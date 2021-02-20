package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author ding
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_ok,id:  " + id;
    }

    /**
     * 模拟超时
     *
     * 服务的降级fallback
     * HystrixCommand 注解的作用是一旦调用该方法失败会自动调用注解内标注的方法
     *
     *设置自身调用的峰值时间
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeNums = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNums);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_Timeout,id:  " + id + "耗时间" + timeNums;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "8001服务繁忙" + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler,id:  " + id;
    }
}
