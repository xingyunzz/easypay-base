package com.xingyun.easypayadmin.component;

import lombok.Data;

@Data
public class SysUserInfo {

    private String token;

    private Long userId;

    private String nickname;

    /**
     * 账户
     */
    private String account;

    /**
     * 密钥
     */
    private String gaSecretKey;

}
