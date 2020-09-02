package com.zty.ssm.service;

import com.zty.ssm.domain.Permission;
import com.zty.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOterPermission(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionids) throws Exception;
}
