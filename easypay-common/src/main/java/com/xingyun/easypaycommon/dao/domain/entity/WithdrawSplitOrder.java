package com.xingyun.easypaycommon.dao.domain.entity;

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
 * 提现拆单表
 * </p>
 *
 * @author xingyun
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="WithdrawSplitOrder对象", description="提现拆单表")
public class WithdrawSplitOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String transactionId;

    @ApiModelProperty(value = "商户订单号")
    private String outOrderNo;

    @ApiModelProperty(value = "拆单订单号")
    private String splitOrderNo;

    @ApiModelProperty(value = "用户编码")
    private String amount;

    @ApiModelProperty(value = "昵称")
    private String totalAmount;

    @ApiModelProperty(value = "0派单中 1完成 2超时 3手动完成")
    private Integer splitStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
