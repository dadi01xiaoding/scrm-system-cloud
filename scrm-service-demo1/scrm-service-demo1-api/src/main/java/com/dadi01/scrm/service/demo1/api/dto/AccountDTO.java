package com.dadi01.scrm.service.demo1.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fangming
 * @description
 * @date 2020/1/7 14:42
 */
@Data
@Accessors(chain = true)
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 1022946177822677939L;
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
