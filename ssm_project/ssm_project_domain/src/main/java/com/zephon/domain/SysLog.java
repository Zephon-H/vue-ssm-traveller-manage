package com.zephon.domain;

import com.zephon.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 日志
 */
@Getter
@Setter
@ToString
public class SysLog {
    private String id;
    /**
     * 访问时间
     */
    private Date visitTime;
    /**
     * 访问时间字符串
     */
    private String visitTimeStr;
    /**
     * 访问用户名
     */
    private String username;
    /**
     * 访问者ip
     */
    private String ip;
    /**
     * 访问资源url
     */
    private String url;
    /**
     * 执行时间
     */
    private Long executionTime;
    /**
     * 执行方法
     */
    private String method;

    public String getVisitTimeStr() {
        if(visitTime!=null){
            visitTimeStr = DateUtil.date2String(visitTime,"yyyy-MM-dd HH:mm");
        }
        return visitTimeStr;
    }
}
