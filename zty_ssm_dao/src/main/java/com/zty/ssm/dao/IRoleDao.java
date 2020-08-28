package com.zty.ssm.dao;

import com.zty.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {
    //根据用户ID查询所有的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    public List<Role> findRoleByUserId(String userId) throws Exception;
}
