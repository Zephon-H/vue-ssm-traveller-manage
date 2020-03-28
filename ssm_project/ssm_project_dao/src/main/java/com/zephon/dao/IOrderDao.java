package com.zephon.dao;

import com.zephon.domain.Member;
import com.zephon.domain.Order;
import com.zephon.domain.Product;
import com.zephon.domain.Traveller;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/24 下午2:21
 * @Copyright ©
 */
@Repository("orderDao")
public interface IOrderDao {

    @Select("select * from tb_order")
    @Results({
            @Result(id =true,property = "id",column = "id"),
            @Result(property = "product",column = "id",javaType = Product.class,
            one = @One(select = "com.zephon.dao.IProductDao.findByOrderId"))
    })
    List<Order> findAll();

    @Select("select * from tb_order where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "product",column = "id",javaType = Product.class,
                    one = @One(select = "com.zephon.dao.IProductDao.findByOrderId")),
            @Result(property = "member",column = "member_id",javaType = Member.class,
                    one = @One(select = "com.zephon.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.zephon.dao.ITravellerDao.findByOrderId"))
    })
    Order findById(String id);

    @Update("update tb_order set order_num=#{orderNum},order_time=#{orderTime},people_count=#{peopleCount},order_desc=#{orderDesc},pay_type=#{payType} where id=#{id}")
    void saveOrder(Order o);

    @Update("update tb_traveller set name=#{name},gender=#{gender},phone_num=#{phoneNum},credentials_type=#{credentialsType},credentials_num=#{credentialsNum},traveller_type=#{travellerType}  where id=#{id}")
    void saveTraveller(Traveller traveller);

    @Update("update tb_member set name=#{name},nickname=#{nickname},phone_num=#{phoneNum},email=#{email} where id=#{id}")
    void saveMember(Member member);
}
