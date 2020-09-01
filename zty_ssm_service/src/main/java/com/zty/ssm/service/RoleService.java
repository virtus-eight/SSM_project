package com.zty.ssm.service;

import com.zty.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws Exception;

}
