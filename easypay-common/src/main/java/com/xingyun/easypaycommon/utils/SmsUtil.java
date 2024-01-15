package com.xingyun.easypaycommon.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.xingyun.easypaycommon.base.HttpUtil;
import com.xingyun.easypaycommon.exception.BizException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.Properties;

public class SmsUtil {
    private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    private static final String md5Key;
    private static final String orgCode;
    private static final String template;
    private static final String url;

    static {
        Properties properties = LoadLocalConfig.properties;
        md5Key = properties.getProperty("sms.md5.key");
        orgCode = properties.getProperty("sms.org.code");
        template = properties.getProperty("sms.template");
        url = properties.getProperty("sms.url");
    }


    public static void sendSms(String code,String phone){
        String content = String.format(template,code);
        String rand = RandomUtil.randomNumbers(6);
        try {
            // param
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("orgCode=").append(orgCode)
                    .append("&mobileArea=").append(URLEncoder.encode("+0", "UTF-8"))
                    .append("&mobiles=").append(URLEncoder.encode(phone, "UTF-8"))
                    .append("&content=").append(URLEncoder.encode(URLEncoder.encode(content, "utf-8"), "utf-8"))
                    .append("&rand=").append(rand);
            String sign = DigestUtil.md5Hex(orgCode + content + rand + md5Key).toUpperCase();
            stringBuilder.append("&sign=").append(sign);


            // request
            okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, stringBuilder.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = HttpUtil.getOkClient().newCall(request).execute();
            String result = response.body().string();
            response.close();
            logger.info("短信发送返回值:{}",result);
            //{"code":200,"data":{"sendCode":"20231003180124WTTHP"},"message":"Submitted successfully"}
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.getIntValue("code") != 200){
                logger.error("短信发送失败:{},{}",phone,jsonObject.getString("message"));
                throw new BizException("发送失败,请稍后再试");
            }
        }catch (Exception e){
            logger.error("短信发送失败:{}",phone,e);
            throw new BizException("发送失败,请稍后再试");
        }
    }

    public static void main(String[] args) {
        sendSms("643966","8616774321274");
    }
}
