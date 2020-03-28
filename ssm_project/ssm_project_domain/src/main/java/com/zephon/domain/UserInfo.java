package com.zephon.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 用户
 */
@Getter
@Setter
@ToString
public class UserInfo implements Serializable {
    private String id;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 用户状态
     * 0：未启用
     * 1：已启用
     */
    private Integer status;
    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户对应的角色
     */
    private Set<Role> roles;

}

