package com.loop.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.loop.demo.annotation.IgnoreUserToken;
import com.loop.demo.config.UserAuthConfig;
import com.loop.demo.util.JwtUtil;
import com.loop.demo.vo.JWTInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class UserAuthRestInterceptor implements HandlerInterceptor {

    @Value("${url.ignore.startWith}")
    private String startWith;

    private final UserAuthConfig userAuthConfig;

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 配置该注解，说明不进行用户拦截  handlerMethod.getBeanType() 获取对象上的注解
            IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
            // 配置该注解，说明不进行用户拦截  handlerMethod.getMethodAnnotation（）获取方法上的注解
            if (annotation == null) {
                annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
            }
            if (annotation != null) {
                return true;
            }
        }
        //获取请求的uri
        String requestUri = request.getRequestURI();
        log.info("requestUri:{}", requestUri);
        //获取请求的方式
        String method = request.getMethod();
        log.info("method:{}", method);
        // 不进行拦截的地址
        if (this.isStartWith(requestUri)) {
            //不进行拦截的地址放行
            return true;
        }

        //校验请求头是否带有token
        String token = request.getHeader(userAuthConfig.getTokenHeader());
        JWTInfo infoFromToken = null;
        try {
            infoFromToken = jwtUtil.getInfoFromToken(token);
        } catch (Exception e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(403);
            PrintWriter out = null;
            try {
                JSONObject res = new JSONObject();
                res.put("status", "403");
                res.put("message", e.getMessage());
                res.put("rel", false);
                res.put("data", "");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            } catch (Exception e2) {
                return false;
            }
        }
        return true;
    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }
}
