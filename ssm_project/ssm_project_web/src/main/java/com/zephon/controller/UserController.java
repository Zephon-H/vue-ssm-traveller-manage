package com.zephon.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zephon.domain.Role;
import com.zephon.domain.UserInfo;
import com.zephon.entity.JWTToken;
import com.zephon.entity.Result;
import com.zephon.entity.ResultCode;
import com.zephon.service.IUserService;
import com.zephon.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller
 * @date 2020/3/21 下午5:37
 * @Copyright ©
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;


    @PostMapping("/user/login")
    public Result login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        password = new Md5Hash(password, username, 3).toString();
        Map<String, Object> m = new HashMap<>();
        m.put("username", username);
        m.put("password", password);
//        UserInfo userInfo = userService.findByUsername(username);
//        if (userInfo.getPassword().equals(password)) {
        String token = jwtUtil.createJwt(null, username, m);
        JWTToken jwtToken = new JWTToken(token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(jwtToken);
//            System.out.println("token:" + token);
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);
            return new Result(ResultCode.SUCCESS, tokenMap);
        } catch (Exception e) {
            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
        }
//        } else {
//            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
//        }


//        if (userInfo.getPassword().equals(password)) {
//            Map<String, Object> m = new HashMap<>();
//            m.put("userId", userInfo.getId());
//            String token = jwtUtil.createJwt(userInfo.getId(), username, m);
//            System.out.println("token:" + token);
//            Map<String, String> tokenMap = new HashMap<>();
//            tokenMap.put("token", token);
//            return new Result(ResultCode.SUCCESS, tokenMap);
//        } else {
//            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
//        }
    }

    @GetMapping("/user/info")
    public Result info(@RequestParam("token") String token) {
        // 获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        // 获取安全数据
        UserInfo result = (UserInfo) principals.getPrimaryPrincipal();

//        Claims claims = jwtUtil.parseJwt(token);
//        System.out.println(claims.get("userId"));
//        UserInfo userInfo = userService.findById((String) claims.get("userId"));
//        request.getSession().setAttribute("username",userInfo.getUsername());
//        userInfo.setPassword("");
        return new Result(ResultCode.SUCCESS, result);
    }
    @PostMapping("/user/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }
    }

    @GetMapping("/users")
    @RequiresPermissions("/user/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<UserInfo> list = userService.findAll(page, limit);
        PageInfo pageInfo = new PageInfo(list);
//        Map<String,Object> map = new HashMap<>();
//        map.put("list",list);
//        map.put("total",list.size());
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    @PostMapping("/user")
    @RequiresPermissions("/user/save")
    public Result save(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        try {
            userService.save(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.INFORMATION_NOT_STANDARD);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/user/upload")
    @RequiresPermissions("/user/upload")
    public Result upload(@RequestParam(name = "file") MultipartFile file) {
        // 保存后，获取到图片地址回显
        String encode = null;
        try {
            encode = "data:image/png;base64," + Base64.encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, encode);
    }

    @GetMapping("/user/{id}")
    @RequiresPermissions("/user/findById")
    public Result findById(@PathVariable("id") String id) {
        return new Result(ResultCode.SUCCESS, userService.findById(id));
    }

    @GetMapping("/user/role/{id}")
    @RequiresPermissions("/user/findOtherRolesById")
    public Result findOtherRolesById(@PathVariable("id") String id) {
        List<Role> list = userService.findOtherRolesById(id);
        return new Result(ResultCode.SUCCESS, list);
    }

    @GetMapping("/user/role")
    @RequiresPermissions("/user/addRole")
    public Result addRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String[] roleIds) {
        userService.addRole(userId, roleIds);
        return new Result(ResultCode.SUCCESS);
    }
    @GetMapping("/unauthorized")
    public Result unauthorized(){
        return new Result(ResultCode.UNAUTHORISE);
    }
    @GetMapping("/unauthenticated")
    public Result unauthenticated(){
        return new Result(ResultCode.UNAUTHENTICATED);
    }
//    @PutMapping("/user/{id}")
//    public Result update(@PathVariable("id") Integer id,@RequestBody UserInfo userInfo){
//        userInfo.setId(id);
//        userService.update(userInfo);
//        return new Result(ResultCode.SUCCESS);
//    }
//
//    @DeleteMapping("/user/{id}")
//    public Result deleteById(@PathVariable("id") Integer id){
//        userService.deleteById(id);
//        return new Result(ResultCode.SUCCESS);
//    }

}

