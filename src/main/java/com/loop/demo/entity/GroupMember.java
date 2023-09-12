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

@ApiModel(value = "com-loop-demo-entity-GroupMember")
@Data
@TableName(value = "base_group_member")
public class GroupMember implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    @TableField(value = "group_id")
    @ApiModelProperty(value = "")
    private String groupId;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "")
    private String userId;

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

    @TableField(value = "attr1")
    @ApiModelProperty(value = "")
    private String attr1;

    @TableField(value = "attr2")
    @ApiModelProperty(value = "")
    private String attr2;

    @TableField(value = "attr3")
    @ApiModelProperty(value = "")
    private String attr3;

    @TableField(value = "attr4")
    @ApiModelProperty(value = "")
    private String attr4;

    @TableField(value = "attr5")
    @ApiModelProperty(value = "")
    private String attr5;

    @TableField(value = "attr6")
    @ApiModelProperty(value = "")
    private String attr6;

    @TableField(value = "attr7")
    @ApiModelProperty(value = "")
    private String attr7;

    @TableField(value = "attr8")
    @ApiModelProperty(value = "")
    private String attr8;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_GROUP_ID = "group_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CRT_TIME = "crt_time";

    public static final String COL_CRT_USER = "crt_user";

    public static final String COL_CRT_NAME = "crt_name";

    public static final String COL_CRT_HOST = "crt_host";

    public static final String COL_UPD_TIME = "upd_time";

    public static final String COL_UPD_USER = "upd_user";

    public static final String COL_UPD_NAME = "upd_name";

    public static final String COL_UPD_HOST = "upd_host";

    public static final String COL_ATTR1 = "attr1";

    public static final String COL_ATTR2 = "attr2";

    public static final String COL_ATTR3 = "attr3";

    public static final String COL_ATTR4 = "attr4";

    public static final String COL_ATTR5 = "attr5";

    public static final String COL_ATTR6 = "attr6";

    public static final String COL_ATTR7 = "attr7";

    public static final String COL_ATTR8 = "attr8";
}