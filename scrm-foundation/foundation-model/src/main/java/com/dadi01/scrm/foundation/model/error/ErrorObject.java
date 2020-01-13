package com.dadi01.scrm.foundation.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 错误信息
 * @author fangming
 * @description
 * @date 2019/11/18 10:52
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject implements Serializable {

    private String code;

    private String message;

}
