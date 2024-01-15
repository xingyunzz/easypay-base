package com.xingyun.easypayapi.component;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson2.JSON;
import com.xingyun.easypayapi.common.LoginSession;
import com.xingyun.easypaycommon.exception.BizException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Data
public class LoginInfo {

    @ApiModelProperty("token")
    private String token;

    private Long memberId;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("邀请码")
    private String userCode;

    @ApiModelProperty("是否设置密码")
    private Boolean passwordFlag;

    @ApiModelProperty("是否设置支付密码")
    private Boolean payPasswordFlag;

    public void updateUserInfoInRedis(StringRedisTemplate redisTemplate) {
        this.updateUserInfoInRedis(redisTemplate, LoginSession.getMemberId());
    }

    public void updateUserInfoInRedis(StringRedisTemplate redisTemplate, Long memberId) {
        String token = redisTemplate.opsForValue().get("USER_TOKEN_MEMBER_ID" + memberId);
        String loginInfoStr = redisTemplate.opsForValue().get("USER_TOKEN" + token);
        if (StringUtils.isBlank(loginInfoStr)) {
            throw new BizException("网络繁忙，请稍后重试");
        }
        LoginInfo loginInfo = JSON.parseObject(loginInfoStr, LoginInfo.class);
        BeanUtil.copyProperties(this, loginInfo, CopyOptions.create().ignoreNullValue());
        redisTemplate.opsForValue().set("USER_TOKEN" + token, JSON.toJSONString(loginInfo), 7, TimeUnit.DAYS);
    }

}
