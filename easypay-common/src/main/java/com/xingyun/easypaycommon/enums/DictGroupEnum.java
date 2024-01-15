package com.xingyun.easypaycommon.enums;


public enum DictGroupEnum {

    withdraw_amount_options("withdraw_amount_options", "提现金额选项"),




    ;


    private String key;
    private String desc;

    DictGroupEnum(String key, String desc) {
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
