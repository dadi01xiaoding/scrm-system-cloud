package com.dadi01.scrm.foundation.model.dto;

import com.dadi01.scrm.foundation.model.error.ErrorObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description 返回结果封装类
 * @author fangming
 * @date 2019/11/17 14:57
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ResultDTO<T> extends AbstractResult {

	private static final long serialVersionUID = 4441702795077671385L;

	/**
	 * 数据
	 */
	private T data;

	ResultDTO(Status status){
		this.status = status.getFlag();
	}


	/**
	 * 无数据成功结果返回
	 * @author FM
	 * @return ResultDTO<Void>
	 */
	public static ResultDTO<Void> success() {
		ResultDTO<Void> result = new ResultDTO<>(Status.SUCCESS);
		return result;
	}

	/**
	 * 无数据失败结果返回
	 * @param error
	 * @return
	 */
	public static ResultDTO<Void> failure(ErrorObject error) {
		ResultDTO<Void> result = new ResultDTO<>(Status.FAILURE);
		result.setError(error);
		return result;
	}

	/**
	 * 有数据成功结果返回
	 * @author FM
	 * @param data
	 * @return ResultDTO<T>
	 */
	public static <T> ResultDTO<T> success(T data) {
		final ResultDTO<T> result = new ResultDTO<>(Status.SUCCESS);
		result.setData(data);
		return result;
	}

}
