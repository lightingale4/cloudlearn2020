package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ding
 * @date 2020-12-27
 */
@Mapper
public interface PaymentDao {

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
    Payment getOneById(@Param("id") Long id);


}
