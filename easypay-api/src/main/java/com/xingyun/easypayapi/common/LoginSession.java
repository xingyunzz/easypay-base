package com.xingyun.easypayapi.common;

import com.xingyun.easypayapi.component.LoginInfo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class LoginSession {

    private static final String SECURITY_CONTEXT_ATTRIBUTES = "SECURITY_CONTEXT";

    public static void setContext(LoginInfo context) {
        RequestContextHolder.currentRequestAttributes().setAttribute(
                SECURITY_CONTEXT_ATTRIBUTES,
                context,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static LoginInfo getMemberInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Object attribute = requestAttributes.getAttribute(SECURITY_CONTEXT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST);
        return (LoginInfo) attribute;
        /*return (LoginInfo)RequestContextHolder.currentRequestAttributes()
                .getAttribute(SECURITY_CONTEXT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST);*/
    }

    public static Long getMemberId() {
        LoginInfo loginDto = getMemberInfo();
        if (loginDto == null) {
            return null;
        } else {
            return loginDto.getMemberId();
        }
    }

}
