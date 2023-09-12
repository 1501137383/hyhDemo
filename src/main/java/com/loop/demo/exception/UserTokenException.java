package com.loop.demo.exception;

import com.loop.demo.constant.CommonConstants;

public class UserTokenException extends BaseException {


    public UserTokenException(String message) {
        super(message, CommonConstants.TOKEN_ERROR_CODE);
    }
}
