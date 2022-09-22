package com.hznu.proxy.dynamicProxy;

/**
 * @author LIN
 * @date 2022/9/21 16:33
 */
public class Main {
    public static void main(String[] args) {
        Object proxy = JdkProxyFactory.getProxy(new SmsServiceImpl());
    }
}
