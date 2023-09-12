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

@ApiModel(value = "com-loop-demo-entity-GateLog")
@Data
@TableName(value = "gate_log")
public class GateLog implements Serializable {
    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "序号")
    private Integer id;

    /**
     * 菜单
     */
    @TableField(value = "menu")
    @ApiModelProperty(value = "菜单")
    private String menu;

    /**
     * 操作
     */
    @TableField(value = "opt")
    @ApiModelProperty(value = "操作")
    private String opt;

    /**
     * 资源路径
     */
    @TableField(value = "uri")
    @ApiModelProperty(value = "资源路径")
    private String uri;

    /**
     * 操作时间
     */
    @TableField(value = "crt_time")
    @ApiModelProperty(value = "操作时间")
    private Date crtTime;

    /**
     * 操作人ID
     */
    @TableField(value = "crt_user")
    @ApiModelProperty(value = "操作人ID")
    private String crtUser;

    /**
     * 操作人
     */
    @TableField(value = "crt_name")
    @ApiModelProperty(value = "操作人")
    private String crtName;

    /**
     * 操作主机
     */
    @TableField(value = "crt_host")
    @ApiModelProperty(value = "操作主机")
    private String crtHost;

    @TableField(value = "request")
    @ApiModelProperty(value = "")
    private String request;

    @TableField(value = "respone")
    @ApiModelProperty(value = "")
    private String respone;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MENU = "menu";

    public static final String COL_OPT = "opt";

    public static final String COL_URI = "uri";

    public static final String COL_CRT_TIME = "crt_time";

    public static final String COL_CRT_USER = "crt_user";

    public static final String COL_CRT_NAME = "crt_name";

    public static final String COL_CRT_HOST = "crt_host";

    public static final String COL_REQUEST = "request";

    public static final String COL_RESPONE = "respone";
}