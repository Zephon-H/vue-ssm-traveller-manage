package com.zephon.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.domain
 * 角色
 */
@Getter
@Setter
@ToString
public class Role {
    private String id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色详情
     */
    private String roleDesc;
    /**
     * 角色对应的权限
     */
    private List<Permission> permissions;
    /**
     * 角色对应的用户
     */
    private List<UserInfo> userInfos;
}
