package com.loop.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-loop-demo-entity-User")
@Data
@TableName(value = "base_user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    @TableField(value = "username")
    @ApiModelProperty(value = "")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value = "")
    private String password;

    @TableField(value = "name")
    @ApiModelProperty(value = "")
    private String name;

    @TableField(value = "birthday")
    @ApiModelProperty(value = "")
    private String birthday;

    @TableField(value = "address")
    @ApiModelProperty(value = "")
    private String address;

    @TableField(value = "mobile_phone")
    @ApiModelProperty(value = "")
    private String mobilePhone;

    @TableField(value = "tel_phone")
    @ApiModelProperty(value = "")
    private String telPhone;

    @TableField(value = "email")
    @ApiModelProperty(value = "")
    private String email;

    @TableField(value = "sex")
    @ApiModelProperty(value = "")
    private String sex;

    @TableField(value = "type")
    @ApiModelProperty(value = "")
    private String type;

    @TableField(value = "status")
    @ApiModelProperty(value = "")
    private String status;

    @TableField(value = "description")
    @ApiModelProperty(value = "")
    private String description;

    @TableField(value = "crt_time")
    @ApiModelProperty(value = "")
    private Date crtTime;

    @TableField(value = "crt_user")
    @ApiModelProperty(value = "")
    private String crtUser;

    @TableField(value = "crt_name")
    @ApiModelProperty(value = "")
    private String crtName;

    @TableField(value = "crt_host")
    @ApiModelProperty(value = "")
    private String crtHost;

    @TableField(value = "upd_time")
    @ApiModelProperty(value = "")
    private Date updTime;

    @TableField(value = "upd_user")
    @ApiModelProperty(value = "")
    private String updUser;

    @TableField(value = "upd_name")
    @ApiModelProperty(value = "")
    private String updName;

    @TableField(value = "upd_host")
    @ApiModelProperty(value = "")
    private String updHost;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_NAME = "name";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_ADDRESS = "address";

    public static final String COL_MOBILE_PHONE = "mobile_phone";

    public static final String COL_TEL_PHONE = "tel_phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_SEX = "sex";

    public static final String COL_TYPE = "type";

    public static final String COL_STATUS = "status";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CRT_TIME = "crt_time";

    public static final String COL_CRT_USER = "crt_user";

    public static final String COL_CRT_NAME = "crt_name";

    public static final String COL_CRT_HOST = "crt_host";

    public static final String COL_UPD_TIME = "upd_time";

    public static final String COL_UPD_USER = "upd_user";

    public static final String COL_UPD_NAME = "upd_name";

    public static final String COL_UPD_HOST = "upd_host";
}