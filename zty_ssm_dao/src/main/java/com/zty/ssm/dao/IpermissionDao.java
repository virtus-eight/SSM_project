package com.zty.ssm.dao;

import com.zty.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IpermissionDao {
    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId=#{roleid})")
    public List<Permission> findPermissioById(String id) throws Exception;
}
