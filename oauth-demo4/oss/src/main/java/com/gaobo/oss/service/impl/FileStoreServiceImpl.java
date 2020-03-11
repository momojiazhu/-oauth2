package com.gaobo.oss.service.impl;

import com.gaobo.oss.mapper.FileUploadMapper;
import com.gaobo.oss.model.FileProperties;
import com.gaobo.oss.model.FileUploadModel;
import com.gaobo.oss.model.UploadFile;
import com.gaobo.oss.service.FileStoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Slf4j
public class FileStoreServiceImpl implements FileStoreService {

    @Autowired
    private FileProperties fileProperties;

    @Resource
    private FileUploadMapper fileUploadMapper;

    /**
     * 事物的控制，同时url写入数据库文件表，文件写入文件夹，听网上说，把写入数据库放前面，
     * 如果文件写入文件夹失败了，就可以回滚，保证数据一致性
     * 返回值为数据库文件表的id，供其他表引用
     * @param uploadFile
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Integer save(UploadFile uploadFile) throws Exception {
        FileUploadModel fileUploadModel = new FileUploadModel();
        fileUploadModel.setUrl(StringUtils.join(uploadFile.getPaths(), "/"));
        fileUploadMapper.insertSelective(fileUploadModel);
        Integer id = fileUploadMapper.selectOne(fileUploadModel).getId();
        String savepath = fileProperties.getSavepath();
        File directory = FileUtils.getFile(new String[] { savepath });
        File saveFile = FileUtils.getFile(directory, (String[]) uploadFile.getPaths().toArray(new String[uploadFile.getPaths().size()]));
        try {
            FileUtils.writeByteArrayToFile(saveFile, uploadFile.getContent());
            return id;
        } catch (IOException e) {
            log.error("保存上传文件时出现异常",e);
            throw e;
        }
    }

    @Override
    public File get(UploadFile uploadFile) throws Exception {
        String savepath = this.fileProperties.getSavepath();
        File directory = FileUtils.getFile(new String[] { savepath });
        File result = FileUtils.getFile(directory, (String[]) uploadFile.getPaths().toArray(new String[uploadFile.getPaths().size()]));
        if (!result.exists()) {
            throw new FileNotFoundException(StringUtils.join(uploadFile.getPaths(), "/") + " not found");
        }

        return result;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean delete(UploadFile uploadFile) throws Exception{
        FileUploadModel fileUploadModel = new FileUploadModel();
        fileUploadModel.setUrl(StringUtils.join(uploadFile.getPaths(), "/"));
        fileUploadMapper.delete(fileUploadModel);
        String savepath = this.fileProperties.getSavepath();
        File directory = FileUtils.getFile(new String[] { savepath });
        File delFile = FileUtils.getFile(directory, (String[]) uploadFile.getPaths().toArray(new String[uploadFile.getPaths().size()]));
        return FileUtils.deleteQuietly(delFile);
    }
}
