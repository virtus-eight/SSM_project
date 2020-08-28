package com.zty.ssm.service.impl;

import com.zty.ssm.dao.IProductDao;
import com.zty.ssm.domain.Product;
import com.zty.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//事务
public class IProductServiceImpl implements IProductService {

    @Autowired
    //装载Dao类
    private IProductDao iProductDao;
    @Override
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }

    //存储
    @Override
    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }
}
