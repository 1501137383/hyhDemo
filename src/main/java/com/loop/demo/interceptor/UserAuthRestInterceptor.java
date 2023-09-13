package com.loop.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.loop.demo.annotation.IgnoreUserToken;
import com.loop.demo.config.UserAuthConfig;
import com.loop.demo.constant.AdminConstant;
import com.loop.demo.context.BaseContextHandler;
import com.loop.demo.entity.Element;
import com.loop.demo.entity.Menu;
import com.loop.demo.exception.UserTokenException;
import com.loop.demo.mapper.ElementRepository;
import com.loop.demo.mapper.MenuRepository;
import com.loop.demo.service.PermissionService;
import com.loop.demo.util.JwtUtil;
import com.loop.demo.vo.JWTInfo;
import com.loop.demo.vo.PermissionInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class UserAuthRestInterceptor implements HandlerInterceptor {

    @Value("${url.ignore.startWith}")
    private String startWith;

    private final UserAuthConfig userAuthConfig;

    private final JwtUtil jwtUtil;

    private final MenuRepository menuRepository;

    private final ElementRepository elementRepository;

    private final PermissionService permissionService;

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

        //校验用户菜单权限
        List<PermissionInfo> permissionInfos = new ArrayList<>();
        List<Menu> menus = menuRepository.selectList(null);
        permissionInfos.addAll(menus.stream().map(menu -> {
            PermissionInfo info = new PermissionInfo();
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }

            info.setCode(menu.getCode());
            info.setType(AdminConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminConstant.RESOURCE_REQUEST_METHOD_GET);
            info.setMenu(menu.getTitle());
            return info;
        }).collect(Collectors.toList()));
        List<Element> elements = elementRepository.getAllElementPermissions();
        permissionInfos.addAll(elements.stream().map(element -> {
                    PermissionInfo info = new PermissionInfo();
                    info.setCode(element.getCode());
                    info.setType(element.getType());
                    info.setUri(element.getUri());
                    info.setMethod(element.getMethod());
                    info.setName(element.getName());
                    info.setMenu(element.getMenuId());
                    return info;
                }
        ).collect(Collectors.toList()));
        permissionInfos = getPermissionInfos(permissionInfos, requestUri, method);
        if (permissionInfos.size() == 0) {
            //否则抛异常
            throw new UserTokenException("不存在的菜单");
        }
        //根据用户的id获取该用户所拥护的权限
        permissionInfos = permissionService.getPermissionByUserId(infoFromToken.getUserId());
        if (getPermissionInfos(permissionInfos, requestUri, method).size() == 0) throw new UserTokenException("用户无权限");

        //如果有则放行
        BaseContextHandler.setName(infoFromToken.getName());
        BaseContextHandler.setUserId(infoFromToken.getUserId());
        BaseContextHandler.setUserToken(token);
        return true;
    }


    /**
     * 判断用户的权限
     *
     * @param requestUri
     * @param method
     * @return
     */
    private List<PermissionInfo> getPermissionInfos(List<PermissionInfo> serviceInfo, final String requestUri, final String method) {
        return new ArrayList<>(Collections2.filter(serviceInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        }));
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
