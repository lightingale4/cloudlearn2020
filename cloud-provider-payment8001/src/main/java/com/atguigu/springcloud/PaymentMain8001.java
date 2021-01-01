package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * @author ding
 * @date 2020-12-20 23:00
 */
@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(PaymentMain8001.class, args);

        System.out.println("SpringBoot启动好了");



    }
}
