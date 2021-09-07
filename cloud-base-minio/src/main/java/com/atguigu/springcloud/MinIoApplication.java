package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ding
 * minio启动类
 */
@SpringBootApplication
public class MinIoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinIoApplication.class, args);
        System.out.println("minIo启动成功");
    }
}
