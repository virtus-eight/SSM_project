package com.zty.ssm.service.impl;

import com.zty.ssm.dao.IUserDao;
import com.zty.ssm.domain.Role;
import com.zty.ssm.domain.UserInfo;
import com.zty.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo = iUserDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
//      //处理自己用户对象为Userdetails
        //User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //返回一个List集合，集合中装入的是角色的描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list= new ArrayList<>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    //查询所有用户
    @Override
    public List<UserInfo> findAll() throws Exception{
        return iUserDao.findAll();
    }

    //添加用户
    @Override
    public void save(UserInfo userInfo){
        //对密码进行加密
        String encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        //将加密后的密码给对象
        userInfo.setPassword(encode);
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        UserInfo userInfo=iUserDao.findById(id);
        return userInfo;
    }
}
