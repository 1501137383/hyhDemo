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

@ApiModel(value = "com-loop-demo-entity-Element")
@Data
@TableName(value = "base_element")
public class Element implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 资源编码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "资源编码")
    private String code;

    /**
     * 资源类型
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "资源类型")
    private String type;

    /**
     * 资源名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 资源路径
     */
    @TableField(value = "uri")
    @ApiModelProperty(value = "资源路径")
    private String uri;

    /**
     * 资源关联菜单
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value = "资源关联菜单")
    private String menuId;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "")
    private String parentId;

    /**
     * 资源树状检索路径
     */
    @TableField(value = "path")
    @ApiModelProperty(value = "资源树状检索路径")
    private String path;

    /**
     * 资源请求类型
     */
    @TableField(value = "method")
    @ApiModelProperty(value = "资源请求类型")
    private String method;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
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

    public static final String COL_CODE = "code";

    public static final String COL_TYPE = "type";

    public static final String COL_NAME = "name";

    public static final String COL_URI = "uri";

    public static final String COL_MENU_ID = "menu_id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_PATH = "path";

    public static final String COL_METHOD = "method";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CRT_TIME = "crt_time";

    public static final String COL_CRT_USER = "crt_user";

    public static final String COL_CRT_NAME = "crt_name";

    public static final String COL_CRT_HOST = "crt_host";

    public static final String COL_ATTR1 = "attr1";

    public static final String COL_ATTR2 = "attr2";

    public static final String COL_ATTR3 = "attr3";

    public static final String COL_ATTR4 = "attr4";

    public static final String COL_ATTR5 = "attr5";

    public static final String COL_ATTR6 = "attr6";

    public static final String COL_ATTR7 = "attr7";

    public static final String COL_ATTR8 = "attr8";
}