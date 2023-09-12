package com.loop.demo.vo;

import com.loop.demo.groups.JwtAuthenticationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 账号密码封装类
 */
@Data
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    @NotBlank(message = "账户不能为空", groups = {JwtAuthenticationGroup.tokenGroup.class})
    @Size(message = "账户的长度不能大于{max}", max = 30, groups = {JwtAuthenticationGroup.tokenGroup.class})
    private String username;
    @NotBlank(message = "密码不能为空", groups = {JwtAuthenticationGroup.tokenGroup.class})
    @Size(message = "密码的长度不能大于{max}", max = 30, groups = {JwtAuthenticationGroup.tokenGroup.class})
    private String password;


    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtAuthenticationRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
