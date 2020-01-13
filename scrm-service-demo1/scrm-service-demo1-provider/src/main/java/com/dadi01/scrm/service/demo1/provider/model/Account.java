package com.dadi01.scrm.service.demo1.provider.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author fang
 * @Date 2020-01-13 01:07
 **/
@Data
@Accessors(chain = true)
public class Account {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobileNumber;
}
