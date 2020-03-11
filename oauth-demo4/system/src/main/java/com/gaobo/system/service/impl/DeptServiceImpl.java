package com.gaobo.system.service.impl;

import com.gaobo.system.control.request.DeptRequest;
import com.gaobo.system.dao.DeptMapper;
import com.gaobo.system.model.DeptModel;
import com.gaobo.system.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Resource
    DeptMapper deptMapper;

    @Override
    public int insert(DeptModel deptModel) {
        return deptMapper.insertSelective(deptModel);
    }

    @Override
    public int updateById(DeptModel deptModel) {
        return deptMapper.updateByPrimaryKeySelective(deptModel);
    }

    @Override
    public DeptModel getById(Integer id) {
        DeptModel deptModel = new DeptModel();
        deptModel.setId(id);
        deptModel.setIsDel(0);
        return deptMapper.selectOne(deptModel);
    }

    @Override
    public int removeById(Integer id) {
        DeptModel deptModel = new DeptModel();
        deptModel.setId(id);
        deptModel.setIsDel(1);
        return deptMapper.updateByPrimaryKeySelective(deptModel);
    }

    @Override
    public List<DeptModel> selectListByCondition(DeptRequest request) {
        return deptMapper.selectListByCondition(request);
    }

    @Override
    public List<DeptModel> selectListByConditionByPage(DeptRequest request) {
        return deptMapper.selectListByConditionByPage(request);
    }
}
