package com.dadi01.scrm.foundation.model.error;


/**
 * 错误信息
 *
 * @author fangming
 * @description
 * @date 2019/11/25 11:46
 **/
public enum ErrorEnum {

    //系统错误
    SYSTEM_BUSY_ERROR(ErrorCodes.SYSTEM_BUSY_ERROR_CODE, "系统繁忙，请稍后重试"),
    QUERY_ERROR(ErrorCodes.QUERY_ERROR_CODE, "查询失败"),
    OPERATE_ERROR(ErrorCodes.OPERATE_ERROR_CODE, "操作失败"),
    INVALID_PARAMETERS_ERROR(ErrorCodes.INVALID_PARAMETERS_ERROR_CODE, "无效参数：%s");

    //错误编码
    private String code;
    //错误信息
    private String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 获取 Error 对象
     * @param params 用于替换 message 中的占位符
     * @return
     */
    public ErrorObject build(Object... params) {
        String message = String.format(this.getMessage(), params);
        return new ErrorObject(this.getCode(), message);
    }

}

