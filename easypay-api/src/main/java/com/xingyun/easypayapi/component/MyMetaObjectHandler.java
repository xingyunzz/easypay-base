package com.xingyun.easypayapi.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xingyun.easypayapi.common.LoginSession;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null || LoginSession.getMemberInfo() == null) {
            this.setFieldValByName("createBy", 1L, metaObject);
            this.setFieldValByName("updateBy", 1L, metaObject);
            this.setFieldValByName("updateByName", "lottery", metaObject);
            return;
        }

        if (LoginSession.getMemberInfo() != null) {
            this.setFieldValByName("createBy", LoginSession.getMemberInfo().getMemberId(), metaObject);
            this.setFieldValByName("updateBy", LoginSession.getMemberInfo().getMemberId(), metaObject);
            this.setFieldValByName("updateByName", LoginSession.getMemberInfo().getNickname(), metaObject);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null || LoginSession.getMemberInfo() == null) {
            this.setFieldValByName("updateBy", 1L, metaObject);
            this.setFieldValByName("updateByName", "lottery", metaObject);
            return;
        }
        if (LoginSession.getMemberInfo() != null) {
            this.setFieldValByName("updateBy", LoginSession.getMemberInfo().getMemberId(), metaObject);
            this.setFieldValByName("updateByName", LoginSession.getMemberInfo().getNickname(), metaObject);
        }
    }
}
