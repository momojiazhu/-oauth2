package com.gaobo.system.service;

import com.gaobo.system.control.request.DeptRequest;
import com.gaobo.system.model.DeptModel;

import java.util.List;

public interface DeptService {

    int insert(DeptModel deptModel);

    int updateById(DeptModel deptModel);

    DeptModel getById(Integer id);

    int removeById(Integer id);

    List<DeptModel> selectListByCondition(DeptRequest request);

    List<DeptModel> selectListByConditionByPage(DeptRequest request);
}
