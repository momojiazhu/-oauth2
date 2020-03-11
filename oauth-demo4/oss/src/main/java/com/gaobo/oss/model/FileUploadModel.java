package com.gaobo.oss.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table( name="file_upload")
public class FileUploadModel {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String url;

    private Date createTime;
}
