package com.loop.demo.controller;

import com.loop.demo.annotation.IgnoreUserToken;
import com.loop.demo.common.RestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@RequestMapping("/test")
@RestController
// @IgnoreUserToken
public class TestController {


    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public RestResponse<String> testV1(
            HttpServletRequest request) throws Exception {
        return new RestResponse<>();
    }


}
