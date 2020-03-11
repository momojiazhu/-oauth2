package com.gaobo.system.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "common_log")
public class CommonLogModel {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String userName;

    private String optObject;

    private String optType;

    private String message;

    private Date createTime;
}
