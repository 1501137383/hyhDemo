package com.loop.demo.controller;

import com.loop.demo.annotation.IgnoreUserToken;
import com.loop.demo.annotation.LogInfo;
import com.loop.demo.common.RestResponse;
import com.loop.demo.entity.GateLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@RequestMapping("/test")
@RestController
@IgnoreUserToken
public class TestController {


    @LogInfo("测试日志接口")
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public RestResponse<String> testV1(
            HttpServletRequest request, HttpServletResponse response, GateLog gateLog) throws Exception {
        return new RestResponse<>();
    }


}
