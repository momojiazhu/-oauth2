package com.gaobo.oss.service;

import com.gaobo.oss.model.UploadFile;

import java.io.File;
import java.io.IOException;

public interface FileStoreService {

    Integer save(UploadFile uploadFile) throws Exception;

    File get(UploadFile uploadFile) throws Exception;

    boolean delete(UploadFile uploadFile) throws Exception;
}
