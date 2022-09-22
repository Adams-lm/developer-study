package com.hznu.proxy.staticProxy;

/**
 * @author LIN
 * @date 2022/9/21 15:50
 */
public class Main {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
