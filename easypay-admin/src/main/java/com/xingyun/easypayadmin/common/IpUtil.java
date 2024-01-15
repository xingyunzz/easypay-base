package com.xingyun.easypayadmin.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

    public static String getIpAddress(HttpServletRequest request){
        String ip = getIp(request);
        if (StringUtils.isBlank(ip)){
            return "";
        }
        return ip.split(",")[0];

    }

    private static String getIp(HttpServletRequest request){
        String ip = request.getHeader("CF-Connecting-IP");

        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");

        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }
        ip = request.getHeader("x-real-ip");

        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (StringUtils.isNotBlank(ip) && !ip.equalsIgnoreCase("unknown")){
            return ip;
        }

        return request.getRemoteAddr();
    }

}
