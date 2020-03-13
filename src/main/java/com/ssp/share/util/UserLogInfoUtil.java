package com.ssp.share.util;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
public class UserLogInfoUtil {

    /*
    获取用户设备类型
     */
    public static String getDeviceType(HttpServletRequest request) {
        // ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String agentString = request.getHeader("User-Agent");  //代理字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(agentString);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem(); // 操作系统信息
        eu.bitwalker.useragentutils.DeviceType deviceType = operatingSystem.getDeviceType(); // 设备类型

        switch (deviceType) {
            case COMPUTER:
                return "PC";
            case TABLET: {
                if (agentString.contains("Android")) return "Android Pad";
                if (agentString.contains("iOS")) return "iPad";
                return "Unknown";
            }
            case MOBILE: {
                if (agentString.contains("Android")) return "Android";
                if (agentString.contains("iOS")) return "IOS";
                return "Unknown";
            }
            default:
                return "Unknown";
        }

    }

    /*
    登录时间
     */
    public static Date getDate() {
        return new Date();
    }

    /*
    IP地址
     */
    public static String getIP(HttpServletRequest request) {
        //获取用户IP地址
        String IP = GetIp.getIpAddress(request);
        return IP;
    }

    /*
    登录方式
     */
    public static String getTypeLogin() {
        return "站点登录";
    }

}
