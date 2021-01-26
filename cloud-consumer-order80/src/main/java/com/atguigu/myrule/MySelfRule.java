package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ding
 * 负载均衡的定义配置类
 * 该类不能配置在主程序所在的包及其子包下(即不能被ComponentScan扫描到)
 */
@Configuration
public class MySelfRule {

    /**
     * 定义为随机
     *
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
