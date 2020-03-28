package com.zephon.dao;

import com.zephon.domain.Permission;
import com.zephon.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.dao
 * @date 2020/3/21 下午10:04
 * @Copyright ©
 */
@Repository("roleDao")
public interface IRoleDao {
    @Select("select * from tb_role where id in " +
            "(select role_id from tb_user_role where user_id=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleDesc", column = "role_desc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.zephon.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findByUserId(String userId);

    @Select("select * from tb_role")
    List<Role> findAll();

    @Insert("insert into tb_role (id,role_name,role_desc) values(#{id},#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from tb_permission where id not in " +
            "(select permission_id from tb_role_permission where role_id=#{roleId})")
    List<Permission> findOtherPermissionByRoleId(String roleId);

    @Insert("insert into tb_role_permission (role_id,permission_id) values(#{arg0},#{arg1})")
    void addPermissionToRole(String roleId, String permissionId);

    @Select("select * from tb_role where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "permissions",column = "id",
                    javaType = java.util.List.class,
                    many = @Many(select = "com.zephon.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String id);

    @Delete("delete from tb_role where id=#{id}")
    void delete(String id);

    @Delete("delete from tb_user_role where role_id=#{id}")
    void deleteFromUserRole(String id);

    @Delete("delete from tb_role_permission where role_id=#{id}")
    void deleteFromRolePermission(String id);
}
