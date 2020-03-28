package com.zephon.service;

import com.zephon.domain.Order;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service
 * @date 2020/3/24 下午2:19
 * @Copyright ©
 */
public interface IOrderService {
    List<Order> findAll(Integer page,Integer pageSize);
    Order findById(String id);

    void saveOrder(Order order);
}
