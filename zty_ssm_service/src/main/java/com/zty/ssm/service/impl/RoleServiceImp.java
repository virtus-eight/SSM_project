package com.zty.ssm.service.impl;

import com.zty.ssm.dao.IRoleDao;
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
        List<Role> list= iRoleDao.findAll();
        return null;
    }
}
