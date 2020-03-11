package com.gaobo.system.control.request;

import lombok.Data;

import java.util.Date;

@Data
public class DeptRequest {

    private Integer id;

    private String deptName;

    private Integer isDel;

    private Date createTimeStart;

    private Date createTimeEnd;

    private Date updateTimeStart;

    private Date updateTimeEnd;

    private Integer currentPage;

    private Integer pageNum;
}
