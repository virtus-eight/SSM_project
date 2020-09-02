package com.zty.ssm.dao;

import com.zty.ssm.domain.Permission;
import com.zty.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    //根据用户ID查询所有的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.zty.ssm.dao.IpermissionDao.findPermissioById"))
    }
    )
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role (id,roleName,roleDesc) values (REPLACE(UUID(),\"-\",\"\"),#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;

    @Select("SELECT * FROM permission WHERE id NOT IN (SELECT permissionId FROM role_permission WHERE roleId =#{roleId})")
    List<Permission> findOterPermission(String roleId) throws Exception;

    @Insert("INSERT INTO role_permission(permissionId,roleId) VALUES (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;
}
