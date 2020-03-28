package com.zephon.service.impl;

import com.zephon.dao.IPermissionDao;
import com.zephon.domain.Permission;
import com.zephon.service.IPermissionService;
import com.zephon.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service.impl
 * @date 2020/3/27 下午2:58
 * @Copyright ©
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permission.setId(UUID.randomUUID().toString().replace("-",""));
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(String id) {
        permissionDao.delete(id);
        permissionDao.deleteFromRolePermission(id);
    }
}
