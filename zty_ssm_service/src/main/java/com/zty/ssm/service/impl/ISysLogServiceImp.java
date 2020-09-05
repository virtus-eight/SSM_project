package com.zty.ssm.service.impl;

import com.zty.ssm.dao.ISysLogDao;
import com.zty.ssm.domain.SysLog;
import com.zty.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ISysLogServiceImp implements ISysLogService {
    @Autowired
    private ISysLogDao iSysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception{
        iSysLogDao.save(sysLog);
    }
}
