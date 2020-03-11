package com.gaobo.oss.service;

import com.gaobo.oss.model.FileUploadModel;

import java.util.List;

public interface FileUploadService {

    public Integer countByUrl(String url);

    public List<FileUploadModel> selectFileList();
}
