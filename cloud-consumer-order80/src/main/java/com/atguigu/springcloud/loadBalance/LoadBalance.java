package com.atguigu.springcloud.loadBalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author ding
 *
 * 该接口用于获取服务提供者的服务列表
 */
public interface LoadBalance {
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList);
}
