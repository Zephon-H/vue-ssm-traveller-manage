package com.zephon.service.impl;

import com.zephon.dao.IRoleDao;
import com.zephon.domain.Permission;
import com.zephon.domain.Role;
import com.zephon.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service.impl
 * @date 2020/3/27 下午2:37
 * @Copyright ©
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        role.setId(UUID.randomUUID().toString().replace("-",""));
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(String roleId) {
        return roleDao.findOtherPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionsToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        roleDao.delete(id);
        roleDao.deleteFromUserRole(id);
        roleDao.deleteFromRolePermission(id);
    }
}
