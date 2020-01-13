package com.dadi01.scrm.foundation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 * @author fangming
 * @description
 * @date 2019/11/18 18:37
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> implements Serializable {

    /**
     * 数据集合
     */
    private List<T> data;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

}
