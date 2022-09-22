package com.hznu.proxy.dynamicProxy;

import com.hznu.proxy.staticProxy.SmsService;

/**
 * @author LIN
 * @date 2022/9/21 15:48
 */
public class SmsServiceImpl implements SmsService {

    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }

}
