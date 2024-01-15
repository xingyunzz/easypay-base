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
 * 充值表
 * </p>
 *
 * @author xingyun
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Deposit对象", description="充值表")
public class Deposit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "代理商码")
    private String agentCode;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "代理商码")
    private String withdrawSplitOrderNo;

    private String withdrawTransactionId;

    private String transactionId;

    @ApiModelProperty(value = "商户充值订单号")
    private String outOrderNo;

    @ApiModelProperty(value = "1银行卡 2支付宝 3微信")
    private Integer type;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "手续费")
    private BigDecimal fee;

    @ApiModelProperty(value = "附件")
    private String attach;

    @ApiModelProperty(value = "0支付中 1支付完成 2取消支付")
    private Integer withdrawStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
