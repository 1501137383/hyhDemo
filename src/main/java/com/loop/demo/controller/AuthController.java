package com.loop.demo.controller;

import com.loop.demo.common.RestResponse;
import com.loop.demo.config.UserAuthConfig;
import com.loop.demo.context.BaseContextHandler;
import com.loop.demo.groups.JwtAuthenticationGroup;
import com.loop.demo.service.UserService;
import com.loop.demo.vo.JwtAuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录服务器
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("jwt")
public class AuthController {
    private Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    private final UserAuthConfig userAuthConfig;

    /**
     * 获取用户的token
     *
     * @param jwtAuthenticationRequest 账号密码封装类
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public RestResponse<?> createAuthenticationToken(@RequestBody @Validated(value = JwtAuthenticationGroup.tokenGroup.class) JwtAuthenticationRequest jwtAuthenticationRequest) {
        log.info("创建token");
        return new RestResponse<>().data(userService.createAuthenticationToken(jwtAuthenticationRequest));
    }

    /**
     * 刷新token 传入旧的token 获取用户信息  然后根据用户信息构造新的token
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public RestResponse<String> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(userAuthConfig.getTokenHeader());
        String refreshedToken = userService.refreshAndGetAuthenticationToken(token);
        return new RestResponse<String>().data(refreshedToken);
    }

    /**
     * 传入token  获取用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public RestResponse<?> verify(String token) {
        userService.verify(token);
        return new RestResponse<>();
    }
}
