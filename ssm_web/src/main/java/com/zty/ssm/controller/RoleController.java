package com.zty.ssm.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zty.ssm.domain.Permission;
import com.zty.ssm.domain.Role;
import com.zty.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //根据roleId查询role，并查询可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "roleId",required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role roleList = roleService.findById(roleId);
        //根据roleid查询可以添加的权限
        List<Permission> permissionList=roleService.findOterPermission(roleId);
        mv.addObject("role",roleList);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //添加权限给角色
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "permissionids",required = true) String[] permissionids) throws Exception{
        roleService.addPermissionToRole(roleId,permissionids);
        return "redirect:findAll.do";
    }
}
