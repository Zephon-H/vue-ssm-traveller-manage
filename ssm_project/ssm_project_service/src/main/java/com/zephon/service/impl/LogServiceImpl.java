package com.zephon.service.impl;

import com.github.pagehelper.PageHelper;
import com.zephon.dao.ILogDao;
import com.zephon.domain.SysLog;
import com.zephon.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service.impl
 * @date 2020/3/28 下午1:17
 * @Copyright ©
 */
@Service("logService")
public class LogServiceImpl implements ILogService {
    @Autowired
    private ILogDao logDao;
    @Override
    public List<SysLog> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return logDao.findAll();
    }

    @Override
    public void save(SysLog log) {
        log.setId(UUID.randomUUID().toString().replace("-",""));
        logDao.save(log);
    }
}
