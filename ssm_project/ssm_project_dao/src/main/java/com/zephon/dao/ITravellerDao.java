package com.zephon.dao;

import com.zephon.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/24 下午6:34
 * @Copyright ©
 */
@Repository("travellerDao")
public interface ITravellerDao {
    @Select("select * from tb_traveller where id in (select traveller_id from tb_order_traveller where order_id=#{orderId} )")
    List<Traveller> findByOrderId(String orderId);
}
