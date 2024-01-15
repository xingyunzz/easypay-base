package com.xingyun.easypaycommon.enums;


public enum DictKeyEnum {

    download_page_url("download_page_url", "第三方分发页面地址"),
    ios_download_url("ios_download_url", "苹果app下载地址"),

    android_download_url("android_download_url", "安卓app下载地址"),
    h5_url_list("h5_url_list", "h5域名列表"),
    default_agent_user_code("default_agent_user_code", "默认邀请码"),
    default_agent_scheme("default_agent_scheme", "默认邀请方案"),
    total_agent_user_code("total_agent_user_code", "总代邀请码"),
    total_agent_scheme("total_agent_scheme", "总代邀请方案"),
    //usdt_exchange_rate("usdt_exchange_rate", "USDT汇率"),

    usdt_exchange_in_rate("usdt_exchange_in_rate", "USDT充值汇率"),
    usdt_exchange_out_rate("usdt_exchange_out_rate", "USDT提现汇率"),

    share_page_url("share_page_url", "分享页面路径"),

    withdraw_amount_options_alipay("withdraw_amount_options_alipay", "提现金额选项"),
    withdraw_amount_options_bankcard("withdraw_amount_options_bankcard", "提现金额选项"),
    withdraw_amount_options_usdt("withdraw_amount_options_usdt", "提现金额选项"),
    withdraw_amount_options_kdou("withdraw_amount_options_kdou", "提现金额选项"),


    customer_service_url("customer_service_url", "客服链接"),

    ;


    private String key;
    private String desc;

    DictKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }


    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
