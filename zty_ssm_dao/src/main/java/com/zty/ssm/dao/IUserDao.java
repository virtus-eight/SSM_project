package com.zty.ssm.dao;

import com.zty.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface  IUserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(id = true,property = "username",column = "username"),
            @Result(id = true,property = "email",column = "email"),
            @Result(id = true,property = "password",column = "password"),
            @Result(id = true,property = "phoneNum",column = "phoneNum"),
            @Result(id = true,property = "status",column = "status"),
            @Result(id = true,property = "roles",column = "id",javaType =List.class,many =@Many(select = "com.zty.ssm.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findByUsername(String username) throws Exception;
}
