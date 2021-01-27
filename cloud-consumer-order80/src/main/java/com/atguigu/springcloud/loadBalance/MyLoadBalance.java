package com.atguigu.springcloud.loadBalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 仿照写一个轮询
 */
@Component
public class MyLoadBalance implements LoadBalance {

    private AtomicInteger atomic = new AtomicInteger(0);

    /**
     * 该方法主要通过自选和cas来模拟获取请求服务的次数
     *
     * @return
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            //判断当前值
            current = this.atomic.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomic.compareAndSet(current, next));
        System.out.println("这是第几次访问" + next);
        return next;
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {

        /**
         * 根据上面的方法获取访问次数，然后对服务列表进行取模获取下标
         */
        int index = getAndIncrement() % serviceInstanceList.size();
        ServiceInstance serviceInstance = serviceInstanceList.get(index);
        return serviceInstance;
    }
}
