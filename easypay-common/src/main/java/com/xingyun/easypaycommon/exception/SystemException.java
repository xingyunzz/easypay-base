package com.xingyun.easypaycommon.exception;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SystemException() {
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
    }

}