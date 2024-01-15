package com.xingyun.easypaycommon.exception;

import com.xingyun.easypaycommon.base.ResultCode;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Object... args) {
        super(String.format(message, args));
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.msg);
        this.code = resultCode.code;
    }
    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BizException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}