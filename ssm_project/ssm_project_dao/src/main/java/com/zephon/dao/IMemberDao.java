package com.zephon.dao;

import com.zephon.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/24 下午6:31
 * @Copyright ©
 */
@Repository("memberDao")
public interface IMemberDao {
    @Select("select * from tb_member where id = #{memberId}")
    Member findById(String memberId);
}
