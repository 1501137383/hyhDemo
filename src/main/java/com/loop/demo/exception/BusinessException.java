package com.loop.demo.exception;

import com.loop.demo.constant.CommonConstants;

public class BusinessException extends BaseException{



    public BusinessException(String message) {
        super(message, CommonConstants.TOKEN_ERROR_CODE);
    }
}
