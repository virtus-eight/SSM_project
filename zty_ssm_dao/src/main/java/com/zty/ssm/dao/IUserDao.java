package com.zty.ssm.dao;

import com.zty.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
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
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},# {username},#{password},#{phoneNum},#{status}")
    void save(UserInfo userInfo);
}
