package com.gaobo.system.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Table(name = "dept")
@ApiModel(value = "科室信息")
public class DeptModel {

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "科室ID")
    private Integer id;

    @NotEmpty(message="部门名称不能为空")
    @ApiModelProperty(value = "科室名称")
    private String deptName;

    @ApiModelProperty(value = "删除标志")
    private Integer isDel;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
