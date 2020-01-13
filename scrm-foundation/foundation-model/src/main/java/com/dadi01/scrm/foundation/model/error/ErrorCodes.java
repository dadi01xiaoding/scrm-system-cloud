package com.dadi01.scrm.foundation.model.error;


/**
 * 错误信息
 *
 * @author fangming
 * @description
 * @date 2019/11/25 11:46
 * 错误编号
 * 一共由8位数字进行表示：
 *
 * 前三位表示不同服务名称，统一分配数字
 * 后续两位表示模块名称，由服务负责人统一分配数字
 * 后续三位表示具体错误原因
 * 00401005按照上面的的规则，可划分为004，01，005。04标识user服务代号，01标识登录注册模块，005是业务错误如"该用户不存在"
 **/
public class ErrorCodes {

    //通用（000）
    public static final String RPC_ERROR = "00001001";
    public static final String INVALID_PARAMETERS_ERROR = "00001002";

    //网关（001）
    public static final String ROUTE_ERROR = "00101001";

    /**
     * 获取 Error 对象
     * @param message 错误信息
     * @return
     */
    public static ErrorObject build(String code,String message) {
        return new ErrorObject(code, message);
    }

}
