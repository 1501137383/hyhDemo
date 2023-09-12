package com.loop.demo.service;

import com.loop.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loop.demo.exception.UserTokenException;
import com.loop.demo.vo.JwtAuthenticationRequest;

public interface UserService extends IService<User> {


    String createAuthenticationToken(JwtAuthenticationRequest jwtAuthenticationRequest);

    void verify(String token);

    String refreshAndGetAuthenticationToken(String userToken);

}


