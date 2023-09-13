package com.loop.demo.annotation;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogInfo {

    /**
     * 记录当前操作
     */
    String value() default "";

    /**
     * 日志默认将切点参数都作为参数记录，该方法提供了过滤，
     * 如果有不需要的参数，则该参数提供作用。
     */
    Class<?>[] parameterFilters() default {ServletRequest.class, ServletResponse.class};


}
