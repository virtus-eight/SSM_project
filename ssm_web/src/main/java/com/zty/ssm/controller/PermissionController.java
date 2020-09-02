package com.zty.ssm.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zty.ssm.domain.Permission;
import com.zty.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = iPermissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Permission permission) throws Exception {
        iPermissionService.save(permission);
        return "redirect:findAll.do";
    }
}
