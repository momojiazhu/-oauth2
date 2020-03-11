package com.gaobo.system.service.impl;

import com.gaobo.system.dao.SearchLogMapper;
import com.gaobo.system.model.SearchLogModel;
import com.gaobo.system.service.SearchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SearchLogServiceImpl implements SearchLogService {

    @Resource
    SearchLogMapper searchLogMapper;

    @Override
    public int insert(SearchLogModel searchLogModel) {
        return searchLogMapper.insertSelective(searchLogModel);
    }
}
