package com.xingyun.easypaycommon.dao.domain.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 代理表
 * </p>
 *
 * @author xingyun
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Agent对象", description="代理表")
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "代理商码")
    private String agentCode;

    @ApiModelProperty(value = "代理平台名称")
    private String agentName;

    @ApiModelProperty(value = "代理账号")
    private String agentAccount;

    @ApiModelProperty(value = "密码")
    private String agentPassword;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "角色类型 1管理员 2代理 3代理只读")
    private BigDecimal roleType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
