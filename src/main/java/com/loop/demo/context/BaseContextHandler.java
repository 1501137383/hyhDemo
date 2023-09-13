package com.loop.demo.context;

import com.loop.demo.constant.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class BaseContextHandler {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        return map.get(key);
    }

    public static void set(String key, String value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        threadLocal.set(map);
    }


    public static String getUserId() {
        Object obj = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return returnObjectValue(obj);
    }


    public static String getName() {
        Object obj = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(obj);
    }


    public static String getUserToken() {
        Object obj = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return returnObjectValue(obj);
    }

    public static void removeToken() {
        threadLocal.remove();
    }

    public static void setUserId(String userId) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setName(String name) {
        set(CommonConstants.CONTEXT_KEY_USER_NAME, name);
    }


    public static void setUserToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }


    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }
}
