package com.impl;

import com.service.PayService;

public class WxPayService implements PayService {

    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
