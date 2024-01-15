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
 * 代理商积分记录
 * </p>
 *
 * @author xingyun
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AgentBalanceRecord对象", description="代理商积分记录")
public class AgentBalanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "代理商码")
    private String agentCode;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "1充值 2提现 3手续费 4代理充值 5代理提现")
    private Integer type;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
