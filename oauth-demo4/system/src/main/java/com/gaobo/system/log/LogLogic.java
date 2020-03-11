package com.gaobo.system.log;

import com.gaobo.common.constant.LogMessageConstants;
import com.gaobo.common.constant.UserOperationConstants;
import com.gaobo.system.model.CommonLogModel;
import com.gaobo.system.service.CommonLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class LogLogic {

    @Autowired
    CommonLogService commonLogService;

    private String getCommonLogMessage(String username, String optObject, String optType, String content) {
        String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = null;
        if(UserOperationConstants.OPT_ADD.getMessage().equals(optType)){
            msg = MessageFormat.format(LogMessageConstants.MSG_10006.getMessage(),username,curDate, optObject, content);
        }else if(UserOperationConstants.OPT_UPDATE.getMessage().equals(optType)){
            msg = MessageFormat.format(LogMessageConstants.MSG_10007.getMessage(),username,curDate, optObject, content);
        }else if(UserOperationConstants.OPT_DELETE.getMessage().equals(optType)){
            msg = MessageFormat.format(LogMessageConstants.MSG_10008.getMessage(),username,curDate, optObject, content);
        }
        return msg;
    }

    public void saveCommonLog(String username, String optObject, String optType, String content){
        log.info("saveCommonLog");
        log.info("username = {}, optObject = {}, optType = {}, content = {}", username, optObject, optType, content);

        String msg = getCommonLogMessage(username, optObject, optType, content);

        CommonLogModel commonLogModel = new CommonLogModel();
        commonLogModel.setUserName(username);
        commonLogModel.setOptObject(optObject);
        commonLogModel.setOptType(optType);
        commonLogModel.setMessage(msg);

        commonLogService.insert(commonLogModel);

    }

}
