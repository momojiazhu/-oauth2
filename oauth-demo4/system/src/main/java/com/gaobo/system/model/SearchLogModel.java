package com.gaobo.system.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "search_log")
public class SearchLogModel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String userName;

    private String url;

    private String parameter;

    private String result;

    private Date createTime;
}
