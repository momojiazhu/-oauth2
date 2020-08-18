package com.gaobo.system.control;

import com.gaobo.common.constant.ErrorCodesConstants;
import com.gaobo.common.constant.ObjectTypeConstants;
import com.gaobo.common.constant.UserOperationConstants;
import com.gaobo.common.entity.Result;
import com.gaobo.system.control.request.DeptRequest;
import com.gaobo.system.log.LogLogic;
import com.gaobo.system.model.DeptModel;
import com.gaobo.system.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.gaobo.common.entity.Result.ok;

/**
 * @Validated 注解是前端传递的参数校验，校验规则在实体类写明。
 * @PreAuthorize 注解是控制访问权限，是拥有角色的才可以访问。
 *
 *
 */

@RestController
@Slf4j
@Api(description = "测试接口")
public class HelloController {

    @Resource
    DeptService deptService;

    @Autowired
    LogLogic logLogic;

    @GetMapping("system")
    @PreAuthorize("hasAnyRole('admin','normal','user')")
    @ApiOperation("测试接口1")
    public Result system(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return Result.ok(username);
    }

    @GetMapping("user")
    @PreAuthorize("hasAnyRole('user')")
    @ApiOperation("测试接口2")
    public Result user(){
        return Result.ok("user");
    }

    @PostMapping("/dept/add")
    @ApiOperation("添加科室")
    public Result deptAdd(@RequestBody @Validated DeptModel model) {
        log.info("deptAdd()");
        deptService.insert(model);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logLogic.saveCommonLog(username, ObjectTypeConstants.OPT_DEPARTMENT.getMessage(), UserOperationConstants.OPT_ADD.getMessage(), model.getDeptName());
        return Result.ok("添加成功");
    }

    @PostMapping("/dept/update")
    @ApiOperation("修改科室")
    public Result deptUpdate(@RequestBody @Validated DeptModel model) {
        log.info("deptUpdate");
        if(model.getId()==null){
            return Result.error(400, ErrorCodesConstants.E10001.getMessage());
        }
        deptService.updateById(model);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logLogic.saveCommonLog(username, ObjectTypeConstants.OPT_DEPARTMENT.getMessage(), UserOperationConstants.OPT_UPDATE.getMessage(), model.getDeptName());
        return Result.ok("修改成功");
    }

    @PostMapping("/dept/delete/{id}")
    @ApiOperation("删除科室")
    public Result deptDelete(@PathVariable("id") Integer id) {
        log.info("deptDelete");
        DeptModel deptModel = deptService.getById(id);
        if(deptModel == null){
            return Result.error(400,ErrorCodesConstants.E10002.getMessage());
        }
        deptService.removeById(id);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logLogic.saveCommonLog(username, ObjectTypeConstants.OPT_DEPARTMENT.getMessage(), UserOperationConstants.OPT_DELETE.getMessage(), deptModel.getDeptName());
        return Result.ok("删除成功");
    }

    @PostMapping("/dept/select")
    @ApiOperation("查询科室")
    public Result deptSelect(@RequestBody DeptRequest deptRequest) {
        log.info("deptSelect");
        List<DeptModel> list =  deptService.selectListByCondition(deptRequest);
        return Result.ok(list);
    }

    @PostMapping("/dept/pageSelect")
    @ApiOperation("分页查询科室")
    public Result deptPageSelect(@RequestBody DeptRequest deptRequest) {
        log.info("deptPageSelect");
        PageHelper.startPage(deptRequest.getCurrentPage(), deptRequest.getPageNum());
        List<DeptModel> list =  deptService.selectListByConditionByPage(deptRequest);
        PageInfo<DeptModel> pageInfo = new PageInfo<>(list);
        return Result.ok(pageInfo);
    }
}
