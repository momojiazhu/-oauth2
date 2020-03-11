package com.gaobo.oss.control;

import com.gaobo.common.entity.Result;
import com.gaobo.oss.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FIleDBController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("selectFileList")
    public Result selectFileList(){
        return Result.ok(fileUploadService.selectFileList());
    }
}
