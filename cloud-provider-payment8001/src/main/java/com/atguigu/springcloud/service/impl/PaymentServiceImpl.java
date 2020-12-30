package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;


/**
 * @author ding
 */
@Service("PaymentService")
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        Assert.notNull(payment,"对象不能为空");
        return paymentDao.create(payment);
    }

    @Override
    public Payment getOneById(Long id) {
        Assert.notNull(id,"id不能为空");
        Payment paymentById = paymentDao.getOneById(id);
        return paymentById;
    }
}
