package com.gaobo.system.service.impl;

import com.gaobo.system.dao.CommonLogMapper;
import com.gaobo.system.model.CommonLogModel;
import com.gaobo.system.service.CommonLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonLogServiceImpl implements CommonLogService {

    @Resource
    CommonLogMapper commonLogMapper;

    @Override
    public int insert(CommonLogModel commonLogModel) {
        return commonLogMapper.insertSelective(commonLogModel);
    }
}
