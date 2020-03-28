package com.zephon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zephon.utils.DateUtil;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 产品
 */
@Getter
@Setter
@ToString
public class Product {
    private String id;
    /**
     * 产品编号
     */
    private String productNum;
    /**
     * 产品名
     */
    private String productName;
    /**
     * 出发城市
     */
    private String cityName;
    /**
     * 出发时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;
    /**
     * 出发时间字符串
     */
    private String departureTimeStr;
    /**
     * 产品价格
     */
    private Double productPrice;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 产品状态
     * 0：关闭
     * 1：开启
     */
    private Integer productStatus;

    public String getDepartureTimeStr() {
        if(departureTime!=null){
            departureTimeStr = DateUtil.date2String(departureTime,"yyyy-MM-dd HH:mm");
        }
        return departureTimeStr;
    }

}
