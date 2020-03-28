package com.zephon.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 旅客
 */
@Getter
@Setter
@ToString
public class Traveller {

    private String id;
    /**
     * 旅客姓名
     */
    private String name;
    /**
     * 旅客性别
     */
    private String gender;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 证件类型
     * 0：身份证
     * 1：护照
     * 2：军官证
     */
    private Integer credentialsType;
    /**
     * 证件类型字符串
     */
    private String credentialsTypeStr;
    /**
     * 证件号
     */
    private String credentialsNum;
    /**
     * 旅客类型
     * 0：成人
     * 1：儿童
     */
    private Integer travellerType;
    /**
     * 旅客类型字符串
     */
    private String travellerTypeStr;

    public String getCredentialsTypeStr() {
        if (credentialsType == 0) {
            return "身份证";
        } else if (credentialsType == 1) {
            return "护照";
        } else {
            return "军官证";
        }
    }


    public String getTravellerTypeStr() {
        return travellerType == 0 ? "成人" : "儿童";
    }
}
