package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 降级实现类
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK异常，请稍后再试";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut异常，请稍后再试";
    }
}
