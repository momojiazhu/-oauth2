package com.gaobo.common.constant;

import lombok.Data;

public enum LogMessageConstants {

    MSG_10001("{0} 在 {1} 创建了用户：{2}"),
    MSG_10002("{0} 在 {1} 更新了用户：{2}"),
    MSG_10003("{0} 在 {1} 删除了用户：{2}"),
    MSG_10004("{0} 在 {1} 修改了密码"),
    MSG_10005("{0} 在 {1} 重置了{2}的密码"),
    MSG_10009("{0} 在 {1} 为 {2} 分配了 {3} 角色"),
    MSG_10006("{0} 在 {1} 添加了{2}：{3}"),
    MSG_10007("{0} 在 {1} 修改了{2}：{3}"),
    MSG_10008("{0} 在 {1} 删除了{2}：{3}");

    private String message;

    LogMessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
