package com.zephon.dao;

import com.zephon.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/27 下午1:22
 * @Copyright ©
 */
@Repository("permissionDao")
public interface IPermissionDao {
    @Select("select * from tb_permission where id in " +
            "(select permission_id from tb_role_permission where role_id = #{roleId})")
    List<Permission> findPermissionByRoleId(String roleId);

    @Select("select * from tb_permission")
    List<Permission> findAll();

    @Insert("insert into tb_permission (id,permission_name,url) values(#{id},#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from tb_permission where id=#{id}")
    Permission findById(String id);

    @Delete("delete from tb_permission where id=#{id}")
    void delete(String id);

    @Delete("delete from tb_role_permission where role_id=#{id}")
    void deleteFromRolePermission(String id);
}
