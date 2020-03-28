package com.zephon.service;

import com.zephon.domain.Permission;
import com.zephon.domain.Role;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service
 * @date 2020/3/27 下午2:36
 * @Copyright ©
 */
public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    List<Permission> findOtherPermissionByRoleId(String roleId);

    void addPermissionsToRole(String roleId, String[] permissionIds);

    Role findById(String id);

    void delete(String id);
}
