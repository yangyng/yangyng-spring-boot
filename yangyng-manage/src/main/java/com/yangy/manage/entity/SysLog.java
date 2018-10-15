package com.yangy.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author yangy
 * @since 2018-10-15
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysLog对象", description = "操作日志")
public class SysLog {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "描述")
    private String desp;

    @ApiModelProperty(value = "操作ip")
    private String ip;

    @ApiModelProperty(value = "请求方式")
    private String type;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "权限")
    private String menu;

    @ApiModelProperty(value = "请求路径")
    private String url;

    @ApiModelProperty(value = "操作时间")
    private Long ctime;


}
