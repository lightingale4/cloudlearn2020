package com.atguigu.springcloud.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ding
 * @date 2020-1-1
 */
@Configuration
public class ApplicationConfiguration {


    /**
     * 通过标记LoadBalanced注解，使得restTemplate可以实现负载均衡
     */
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
