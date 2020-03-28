package com.zephon.service;

import com.zephon.domain.SysLog;

import java.util.List;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.service
 * @date 2020/3/28 下午1:17
 * @Copyright ©
 */
public interface ILogService {
    List<SysLog> findAll(Integer page,Integer pageSize);

    void save(SysLog log);
}
