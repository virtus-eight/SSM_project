package com.zty.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.zty.ssm.dao.IOrdersDao;
import com.zty.ssm.domain.Orders;
import com.zty.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrdersServiceImp implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
