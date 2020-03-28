package com.zephon.service.impl;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zephon.dao.IUserDao;
import com.zephon.domain.Role;
import com.zephon.domain.UserInfo;

import com.zephon.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service.impl
 * @date 2020/3/21 下午5:43
 * @Copyright ©
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public UserInfo findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<UserInfo> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
//
//        Integer pageLimit = (page-1)*pageSize;
//        List<UserInfo> list = userDao.findAll(pageLimit,pageSize);
//        System.out.println(list);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        String password = userInfo.getPassword();
        password = new Md5Hash(password, userInfo.getUsername(), 3).toString();
        userInfo.setPassword(password);
        userInfo.setId(UUID.randomUUID().toString().replace("-",""));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRolesById(String id) {
        return userDao.findOtherRolesById(id);
    }

    @Override
    public void addRole(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRole(userId,roleId);
        }
    }
}
