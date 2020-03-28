package com.zephon.controller;

import com.github.pagehelper.PageInfo;
import com.zephon.domain.Permission;
import com.zephon.domain.Role;
import com.zephon.entity.Result;
import com.zephon.entity.ResultCode;
import com.zephon.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller
 * @date 2020/3/27 下午2:34
 * @Copyright ©
 */
@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("/roles")
    @RequiresPermissions("/role/findAll")
    public Result findAll(){
        List<Role> list = roleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return new Result(ResultCode.SUCCESS,pageInfo);
    }

    @PostMapping("/role")
    @RequiresPermissions("/role/save")
    public Result save(@RequestBody Role role){
        try{
            roleService.save(role);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ROLE_INFORMATION_ERROR);
        }
    }
    @GetMapping("/role/permission/{id}")
    @RequiresPermissions("/role/findOtherPermissionByRoleId")
    public Result findOtherPermissionByRoleId(@PathVariable("id") String roleId){
        List<Permission> otherPermissions = roleService.findOtherPermissionByRoleId(roleId);
        return new Result(ResultCode.SUCCESS,otherPermissions);
    }

    @GetMapping("/role/permission")
    @RequiresPermissions("/role/addPermission")
    public Result addPermission(@RequestParam("roleId") String roleId,@RequestParam("permissionIds") String[] permissionIds){
        System.out.println(roleId);
        System.out.println(Arrays.toString(permissionIds));
        try {
            roleService.addPermissionsToRole(roleId,permissionIds);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SERVER_ERROR);
        }
    }
    @GetMapping("role/{id}")
    @RequiresPermissions("/role/findById")
    public Result findById(@PathVariable("id") String id){
        Role role = roleService.findById(id);
        return new Result(ResultCode.SUCCESS,role);
    }

    @DeleteMapping("role/{id}")
    @RequiresPermissions("/role/delete")
    public Result delete(@PathVariable("id") String id){
        try {
            roleService.delete(id);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }
    }

}
