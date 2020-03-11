package com.gaobo.system.dao;

import com.gaobo.system.control.request.DeptRequest;
import com.gaobo.system.model.DeptModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DeptMapper extends Mapper<DeptModel> {

    List<DeptModel> selectListByCondition(DeptRequest request);

    List<DeptModel> selectListByConditionByPage(DeptRequest request);
}
