package com.zephon.controller;

import com.github.pagehelper.PageInfo;
import com.zephon.domain.Permission;
import com.zephon.entity.Result;
import com.zephon.entity.ResultCode;
import com.zephon.service.IPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller
 * @date 2020/3/27 下午2:55
 * @Copyright ©
 */
@RestController
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @GetMapping("/permissions")
    @RequiresPermissions("/permission/findAll")
    public Result findAll(){
        List<Permission> list = permissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return new Result(ResultCode.SUCCESS,pageInfo);
    }
    @PostMapping("/permission")
    @RequiresPermissions("/permission/save")
    public Result save(@RequestBody Permission permission){
        try {
            permissionService.save(permission);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.PERMISSION_INFORMATION_ERROR);
        }
    }
    @GetMapping("/permission/{id}")
    @RequiresPermissions("/permission/findById")
    public Result findById(@PathVariable String id){
        Permission permission = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS,permission);
    }
    @DeleteMapping("/permission/{id}")
    @RequiresPermissions("/permission/delete")
    public Result delete(@PathVariable String id){
        try {
            permissionService.delete(id);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }
    }
}
