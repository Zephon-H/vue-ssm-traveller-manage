package com.zephon.service;

import com.zephon.domain.Product;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service
 * @date 2020/3/23 下午9:58
 * @Copyright ©
 */
public interface IProductService {
    List<Product> findAll(Integer page,Integer pageSize);
    void save(Product product);

    Product findById(String id);

    void update(Product product);
}
