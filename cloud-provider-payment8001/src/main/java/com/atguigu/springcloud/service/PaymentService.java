package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author ding
 *
 */
public interface PaymentService {

    /**
     * 新增
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 查询
     * @param id
     * @return
     */
    Payment getOneById(Long id);
}
