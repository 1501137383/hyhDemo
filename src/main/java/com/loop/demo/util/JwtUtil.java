package com.loop.demo.util;

import com.loop.demo.config.UserAuthConfig;
import com.loop.demo.constant.CommonConstants;
import com.loop.demo.exception.UserTokenException;
import com.loop.demo.vo.JWTInfo;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    @Autowired
    private UserAuthConfig userAuthConfig;

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();


    public String createJWT(String id, String subject, Map<String, Object> map) throws Exception {
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject) // 发行者
                .setIssuedAt(new Date()) // 发行时间
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(userAuthConfig.getUserPriKey())) // 签名类型 与 密钥
                .compressWith(CompressionCodecs.DEFLATE);// 对载荷进行压缩
        if (!CollectionUtils.isEmpty(map)) {
            builder.setClaims(map);
        }
        if (userAuthConfig.getExpire() > 0) {
            builder.setExpiration(new Date(System.currentTimeMillis() + userAuthConfig.getExpire()));
        }
        return builder.compact();
    }


    public Claims parseJWT(String jwtString) throws Exception {
        return Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(userAuthConfig.getUserPubKey()))
                .parseClaimsJws(jwtString)
                .getBody();
    }

    /**
     * 获取token中的用户信息
     */
    private JWTInfo getToken(String token) throws Exception {
        Claims body = parseJWT(token);
        return new JWTInfo(body.getSubject(), getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)), getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
    }

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }


    /**
     * 根据用户的token 获取用户信息
     */
    public JWTInfo getInfoFromToken(String token) {
        try {
            return getToken(token);
        } catch (ExpiredJwtException ex) {
            log.error("登录异常:", ex);
            throw new UserTokenException("User token expired!");
        } catch (SignatureException ex) {
            log.error("登录异常:", ex);
            throw new UserTokenException("User token signature error!");
        } catch (Exception ex) {
            log.error("登录异常:", ex);
            throw new UserTokenException("User token is null or empty!");
        }
    }

}
