package com.zephon.service;

import com.zephon.domain.Permission;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service
 * @date 2020/3/27 下午2:58
 * @Copyright ©
 */
public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String id);

    void delete(String id);
}
