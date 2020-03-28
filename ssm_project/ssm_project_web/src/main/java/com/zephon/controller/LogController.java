package com.zephon.controller;

import com.github.pagehelper.PageInfo;
import com.zephon.domain.SysLog;
import com.zephon.domain.UserInfo;
import com.zephon.entity.Result;
import com.zephon.entity.ResultCode;
import com.zephon.service.ILogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller
 * @date 2020/3/28 下午1:15
 * @Copyright ©
 */
@RestController
public class LogController {
    @Autowired
    private ILogService logService;
    @GetMapping("/logs")
    @RequiresPermissions("/logs/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<SysLog> list = logService.findAll(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }
}
