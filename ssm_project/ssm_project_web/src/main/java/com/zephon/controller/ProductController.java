package com.zephon.controller;

import com.github.pagehelper.PageInfo;
import com.zephon.domain.Product;
import com.zephon.entity.Result;
import com.zephon.entity.ResultCode;
import com.zephon.service.IProductService;
import com.zephon.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller
 * @date 2020/3/23 下午9:56
 * @Copyright ©
 */
@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    @RequiresPermissions("/product/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<Product> list = productService.findAll(page, limit);
        PageInfo pageInfo = new PageInfo(list);
//        Map<String,Object> map = new HashMap<>();
//        map.put("list",all);
//        map.put("total",all.size());
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    @PostMapping("/product")
    @RequiresPermissions("/product/save")
    public Result save(@RequestBody Map<String, String> map) {
        System.out.println(map);
        try {
            String departureTime = (String) map.get("departureTime");
            Date date = DateUtil.string2Date(departureTime, "yyyy-MM-dd HH:mm");
            Product product = new Product();
            product.setProductNum(map.get("productNum"));
            product.setProductName(map.get("productName"));
            product.setCityName(map.get("cityName"));
            product.setDepartureTime(date);
            product.setProductPrice(Double.parseDouble(map.get("productPrice")));
            product.setProductDesc(map.get("productDesc"));
            product.setProductStatus(Integer.parseInt(map.get("productStatus")));
            productService.save(product);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Result(ResultCode.DATE_FORMAT_ERROR);
        }

        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/product/{id}")
    @RequiresPermissions("/product/findById")
    public Result findById(@PathVariable("id") String id){
        Product p = productService.findById(id);
        return new Result(ResultCode.SUCCESS,p);
    }
    @PutMapping("/product")
    @RequiresPermissions("/product/update")
    public Result update(@RequestBody Product product){
        productService.update(product);
        return new Result(ResultCode.SUCCESS);
    }
}
