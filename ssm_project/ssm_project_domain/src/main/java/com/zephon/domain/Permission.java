package com.zephon.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 权限
 */
@Getter
@Setter
@ToString
public class Permission {
    private String id;
    /**
     * 权限名
     */
    private String permissionName;
    /**
     * 权限对应的url
     */
    private String url;
    /**
     * 权限对应的角色
     */
    private List<Role> roles;
}
