package com.loop.demo.config;

import com.loop.demo.util.RsaKeyHelper;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties("jwt")
public class UserAuthConfig implements InitializingBean {
    private static final String REDIS_USER_PUB_KEY = "AUTH:JWT:PUB";
    private static final String REDIS_USER_PRI_KEY = "AUTH:JWT:PRI";
    private static Map<String, String> map = new HashMap<>();


    private String tokenHeader;
    private long expire;
    private String rsaSecret;

    private byte[] userPubKey;

    private byte[] userPriKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (map.get(REDIS_USER_PUB_KEY) == null && map.get(REDIS_USER_PRI_KEY) == null) {
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(rsaSecret);
            map.put(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
            map.put(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
        }
        this.userPubKey = RsaKeyHelper.toBytes(map.get(REDIS_USER_PUB_KEY));
        this.userPriKey = RsaKeyHelper.toBytes(map.get(REDIS_USER_PRI_KEY));
    }
}
