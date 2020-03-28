package com.zephon.service.impl;

import com.github.pagehelper.PageHelper;
import com.zephon.dao.IOrderDao;
import com.zephon.domain.Member;
import com.zephon.domain.Order;
import com.zephon.domain.Product;
import com.zephon.domain.Traveller;
import com.zephon.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service.impl
 * @date 2020/3/24 下午2:20
 * @Copyright ©
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Order> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return orderDao.findAll();
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        Order o = orderDao.findById(order.getId());
        o.setOrderNum(order.getOrderNum());
        o.setOrderTime(order.getOrderTime());
        o.setProduct(order.getProduct());
        o.setOrderDesc(order.getOrderDesc());
        o.setMember(order.getMember());
        o.setTravellers(order.getTravellers());
        o.setPeopleCount(order.getTravellers().size());
        o.setPayType(order.getPayType());
        orderDao.saveOrder(o);
        Member member = o.getMember();
        orderDao.saveMember(member);
        List<Traveller> travellers = o.getTravellers();
        for (Traveller traveller : travellers) {
            orderDao.saveTraveller(traveller);
        }
    }
}
