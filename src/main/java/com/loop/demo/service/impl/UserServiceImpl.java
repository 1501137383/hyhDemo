package com.loop.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.entity.User;
import com.loop.demo.exception.UserTokenException;
import com.loop.demo.mapper.UserRepository;
import com.loop.demo.service.UserService;
import com.loop.demo.util.JwtUtil;
import com.loop.demo.vo.JwtAuthenticationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    private final JwtUtil jwtUtil;

    @Override
    public String createAuthenticationToken(JwtAuthenticationRequest jwtAuthenticationRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "001");
        map.put("name", "zzy");
        try {
            return jwtUtil.createJWT(UUID.randomUUID().toString(), "user-test", map);
        } catch (Exception e) {
            throw new UserTokenException(e.getMessage());
        }
    }
}


