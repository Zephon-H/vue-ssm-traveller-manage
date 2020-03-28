package com.zephon.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 会员信息
 */
@Getter
@Setter
@ToString
public class Member {
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 邮箱
     */
    private String email;

}
