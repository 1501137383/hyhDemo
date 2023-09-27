package com.loop.demo.proxy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //静态代理
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");


        //jdk动态代理
        SmsService smsServiceProxy = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsServiceProxy.send("hello");

        // cglib动态代理
        SmsService cglibServiceProxy = (SmsService) CglibProxyFactory.getProxy(AliSmsServiceImpl.class);
        cglibServiceProxy.send("java");

        QiniuyunSmsService qiniuyunSmsService = (QiniuyunSmsService) CglibProxyFactory.getProxy(QiniuyunSmsService.class);
        qiniuyunSmsService.send("java");


    }
}
