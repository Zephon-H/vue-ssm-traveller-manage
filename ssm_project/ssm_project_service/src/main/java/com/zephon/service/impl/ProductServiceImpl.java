package com.zephon.service.impl;

import com.github.pagehelper.PageHelper;
import com.zephon.dao.IProductDao;
import com.zephon.domain.Product;
import com.zephon.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service.impl
 * @date 2020/3/23 下午9:58
 * @Copyright ©
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        product.setId(UUID.randomUUID().toString().replace("-",""));
        productDao.save(product);
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product p) {
        Product product = productDao.findById(p.getId());
        product.setProductNum(p.getProductNum());
        product.setProductName(p.getProductName());
        product.setCityName(p.getCityName());
        product.setDepartureTime(p.getDepartureTime());
        product.setProductPrice(p.getProductPrice());
        product.setProductDesc(p.getProductDesc());
        product.setProductStatus(p.getProductStatus());
        productDao.update(product);
    }
}
