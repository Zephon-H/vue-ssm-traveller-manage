package com.zephon.dao;

import com.zephon.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/28 下午1:18
 * @Copyright ©
 */
@Repository("logDao")
public interface ILogDao {

    @Select("select * from tb_log")
    List<SysLog> findAll();

    @Insert("insert into tb_log (id,visit_time,username,ip,url,execution_time,method) values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog log);
}
