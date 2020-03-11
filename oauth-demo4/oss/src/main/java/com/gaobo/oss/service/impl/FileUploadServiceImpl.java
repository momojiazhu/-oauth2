package com.gaobo.oss.service.impl;

import com.gaobo.oss.mapper.FileUploadMapper;
import com.gaobo.oss.model.FileUploadModel;
import com.gaobo.oss.service.FileUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    FileUploadMapper fileUploadMapper;

    @Override
    public Integer countByUrl(String url) {
        FileUploadModel fileUploadModel = new FileUploadModel();
        fileUploadModel.setUrl(url);
        return fileUploadMapper.selectCount(fileUploadModel);
    }

    @Override
    public List<FileUploadModel> selectFileList() {
        return fileUploadMapper.selectAll();
    }
}
