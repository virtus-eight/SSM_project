package com.zty.ssm.service.impl;

import com.zty.ssm.dao.IpermissionDao;
import com.zty.ssm.domain.Permission;
import com.zty.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionServiceImp implements IPermissionService {
    @Autowired
    private IpermissionDao ipermissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        List<Permission> permissions=ipermissionDao.findAll();
        return permissions;
    }

    @Override
    public void save(Permission permission) throws Exception {
        ipermissionDao.save(permission);
    }
}
