package com.zty.ssm.dao;

import com.zty.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IpermissionDao {
    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId=#{roleid})")
    public List<Permission> findPermissioById(String id) throws Exception;

    @Select("SELECT * FROM permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(id,permissionName,url) values (REPLACE(UUID(),\"-\",\"\"),#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
