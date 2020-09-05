package com.zty.ssm.dao;

import com.zty.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogDao {
    @Insert("")
    void save(SysLog sysLog) throws Exception;
}
