package com.zty.ssm.service.impl;

import com.zty.ssm.dao.IRoleDao;
import com.zty.ssm.domain.Permission;
import com.zty.ssm.domain.Role;
import com.zty.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOterPermission(String roleId) throws Exception {

        return iRoleDao.findOterPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionids) throws Exception {
        for (String permissionId:permissionids){
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
