package com.zephon.domain;

import com.zephon.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 订单
 */
@Getter
@Setter
@ToString
public class Order {
    private String id;
    /**
     * 订单编号
     */
    private String orderNum;
    /**
     * 下单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;
    /**
     * 下单日期字符串
     */
    private String orderTimeStr;
    /**
     * 订单状态
     * 0：未支付
     * 1：已支付
     */
    private Integer orderStatus;
    /**
     * 订单状态字符串
     */
    private String orderStatusStr;
    /**
     * 出行人数
     */
    private Integer peopleCount;
    /**
     * 订单对应的产品
     */
    private Product product;
    /**
     * 订单对应的游客
     */
    private List<Traveller> travellers;
    /**
     * 订单对应的会员
     */
    private Member member;
    /**
     * 支付类型：
     * 0：支付宝
     * 1：微信
     * 2：其它
     */
    private Integer payType;
    /**
     * 支付类型字符串
     */
    private String payTypeStr;
    /**
     * 订单详情
     */
    private String orderDesc;


    public String getOrderTimeStr() {
        if(orderTime!=null){
            orderTimeStr= DateUtil.date2String(orderTime, "yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public String getOrderStatusStr() {
        return orderStatus==0?"未支付":"已支付";
    }

    public String getPayTypeStr() {
        if(payType==0){
            return "支付宝";
        }else if(payType==1){
            return "微信";
        }else{
            return "其它";
        }
    }
}
