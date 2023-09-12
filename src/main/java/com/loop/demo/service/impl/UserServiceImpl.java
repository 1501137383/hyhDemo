package com.loop.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loop.demo.context.BaseContextHandler;
import com.loop.demo.entity.User;
import com.loop.demo.exception.BaseException;
import com.loop.demo.exception.UserTokenException;
import com.loop.demo.mapper.UserRepository;
import com.loop.demo.service.UserService;
import com.loop.demo.util.JwtUtil;
import com.loop.demo.vo.JWTInfo;
import com.loop.demo.vo.JwtAuthenticationRequest;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    private final JwtUtil jwtUtil;

    private final RedisTemplate redisTemplate;

    private final UserRepository userRepository;


    @Override
    public String createAuthenticationToken(JwtAuthenticationRequest jwtAuthenticationRequest) {
        //通过账号密码查询用户信息
        User user = userRepository.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, jwtAuthenticationRequest.getUsername()).last("LIMIT 1"));
        if (user == null) throw new BaseException("用户不存在");
        if (!user.getPassword().equals(jwtAuthenticationRequest.getPassword())) {
            throw new BaseException("账号或者密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", String.valueOf(user.getId()));
        map.put("name", user.getName());
        try {
            return jwtUtil.createJWT(String.valueOf(user.getId()), "user-subject", map);
        } catch (Exception e) {
            throw new UserTokenException(e.getMessage());
        }
    }

    @Override
    public void verify(String token) {
        try {
            jwtUtil.getInfoFromToken(token);
        } catch (Exception e) {
            throw new UserTokenException(e.getMessage());
        }
    }

    @Override
    public String refreshAndGetAuthenticationToken(String userToken) {
        JWTInfo jwtInfo = null;
        try {
            jwtInfo = jwtUtil.getInfoFromToken(userToken);
        } catch (Exception e) {
            throw new UserTokenException(e.getMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", String.valueOf(jwtInfo.getUserId()));
        map.put("name", jwtInfo.getName());
        try {
            BaseContextHandler.removeToken();
            return jwtUtil.createJWT(String.valueOf(jwtInfo.getUserId()), "user-subject", map);
        } catch (Exception e) {
            throw new UserTokenException(e.getMessage());
        }
    }
}


