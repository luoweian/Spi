package com.impl;

import com.service.PayService;

public class AliPayService implements PayService {

    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
