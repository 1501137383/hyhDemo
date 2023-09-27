package com.loop.demo.proxy;

public class AliSmsServiceImpl implements SmsService {

    @Override
    public void send(String message) {
        System.out.println("send message:" + message);
    }
}
