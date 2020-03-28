package com.zephon.dao;

import com.zephon.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/23 下午10:00
 * @Copyright ©
 */
@Repository("productDao")
public interface IProductDao {
//    @Select("select * from tb_product limit #{arg0},#{arg1}")
//    List<Product> findAll(Integer pageLimit,Integer pageSize);
    @Select("select * from tb_product")
    List<Product> findAll();

    @Insert("insert into tb_product (id,product_num,product_name,city_name,departure_time,product_price,product_desc,product_status) values (#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from tb_product where id in (select product_id from tb_order_product where order_id=#{orderId})")
    Product findByOrderId(String orderId);

    @Select("select * from tb_product where id=#{id}")
    Product findById(String id);

    @Update("update tb_product set product_num=#{productNum},product_name=#{productName},city_name=#{cityName},departure_time=#{departureTime},product_price=#{productPrice},product_desc=#{productDesc},product_status=#{productStatus} where id = #{id}")
    void update(Product product);
}
