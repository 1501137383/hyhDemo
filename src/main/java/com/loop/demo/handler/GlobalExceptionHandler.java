package com.loop.demo.handler;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.loop.demo.common.RestResponse;
import com.loop.demo.exception.BaseException;
import com.loop.demo.exception.UserTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param response
     * @param ex
     * @return
     */
    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserTokenException.class)
    public RestResponse userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(401);
        logger.error(ex.getMessage(), ex);
        return new RestResponse(ex.getStatus(), ex.getMessage());
    }


    @ExceptionHandler(BaseException.class)
    public RestResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(500);
        return new RestResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public RestResponse otherExceptionHandler(HttpServletResponse response, Exception e) {
        logger.error(e.getMessage(), e);
        if (e instanceof MissingServletRequestParameterException) {
            RestResponse result = new RestResponse();
            result.setStatus(500);
            String msg = MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());
            result.setMessage(msg);
            return result;
        }

        if (e instanceof ConstraintViolationException) {
            // 单个参数校验异常
            RestResponse result = new RestResponse();
            Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) e).getConstraintViolations();
            if (CollectionUtils.isNotEmpty(sets)) {
                StringBuilder sb = new StringBuilder();
                sets.forEach(error -> {
                    if (error instanceof FieldError) {
                        sb.append(((FieldError) error).getField()).append(":");
                    }
                    sb.append(error.getMessage()).append(";");
                });
                String msg = sb.toString();
                if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(msg)) {
                    msg = msg.substring(0, msg.length() - 1);

                }
                result.setMessage(msg);
            }
            return result;
        }

        if (e instanceof BindException) {
            //MethodArgumentNotValidException extend BindException
            // posturl请求的对象参数校验异常
            RestResponse result = new RestResponse();
            List<ObjectError> errors = ((BindException) e).getBindingResult().getAllErrors();
            String msg = getValidExceptionMsg(errors);
            if (StringUtils.isNotBlank(msg)) {
                result.setMessage(msg);
            }
            return result;
        }
        response.setStatus(500);
        return new RestResponse(response.getStatus(), e.getMessage());
    }

    private String getValidExceptionMsg(List<ObjectError> errors) {
        if (CollectionUtils.isNotEmpty(errors)) {
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    sb.append(((FieldError)error).getField()).append(":");
                }
                sb.append(error.getDefaultMessage()).append(";");
            });
            String msg = sb.toString();
            if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(msg)) {
                msg = msg.substring(0, msg.length() - 1);

            }
            return msg;
        }
        return null;
    }

}
