package com.zephon.controller;

import com.github.pagehelper.PageInfo;
import com.zephon.domain.Order;
import com.zephon.entity.Result;
import com.zephon.entity.ResultCode;
import com.zephon.service.IOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller
 * @date 2020/3/24 下午2:17
 * @Copyright ©
 */
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/orders")
    @RequiresPermissions("/orders")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<Order> list = orderService.findAll(page, limit);
        PageInfo pageInfo = new PageInfo(list);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }
    @GetMapping("/order/{id}")
    @RequiresPermissions("/order/*")
    public Result findById(@PathVariable("id") String id){
        Order order = orderService.findById(id);
        return new Result(ResultCode.SUCCESS,order);
    }

    @PutMapping("/order")
    @RequiresPermissions("/order")
    public Result saveOrder(@RequestBody Order order){
        try {
            orderService.saveOrder(order);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }
    }
}
