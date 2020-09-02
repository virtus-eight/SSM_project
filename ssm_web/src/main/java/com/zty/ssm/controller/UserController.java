package com.zty.ssm.controller;

import com.zty.ssm.domain.Role;
import com.zty.ssm.domain.UserInfo;
import com.zty.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = iUserService.findAll();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(UserInfo userInfo) throws Exception{
        iUserService.save(userInfo);
        return "redirect:findAll.do";
    }
    //查询指定ID的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        //获取传入的id
        UserInfo userInfo = iUserService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        UserInfo userInfo = iUserService.findById(userid);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles=iUserService.findOtherRoles(userid);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }
    //给用户添加角色
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true) String[] rolesIds){
        iUserService.addRoleToUser(userId,rolesIds);
        return "redirect:findAll.do";
    }
}
