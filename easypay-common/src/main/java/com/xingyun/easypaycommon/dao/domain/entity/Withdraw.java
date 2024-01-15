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
 * 提现表
 * </p>
 *
 * @author xingyun
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Withdraw对象", description="提现表")
public class Withdraw implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "代理商码")
    private String agentCode;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    private String transactionId;

    @ApiModelProperty(value = "商户订单号")
    private String outOrderNo;

    @ApiModelProperty(value = "1银行卡 2支付宝 3微信")
    private Integer type;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "拆单金额")
    private String splitAmount;

    @ApiModelProperty(value = "接收金额")
    private BigDecimal receiveAmount;

    @ApiModelProperty(value = "手续费")
    private BigDecimal fee;

    @ApiModelProperty(value = "0派单中 1完成")
    private Integer withdrawStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
