package com.dadi01.scrm.foundation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页条件类
 * @author fangming
 * @description
 * @date 2019/11/26 16:39
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable{

    private static final long serialVersionUID = 4441702795077671385L;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页显示数量
     */
    private Integer pageSize;
}
