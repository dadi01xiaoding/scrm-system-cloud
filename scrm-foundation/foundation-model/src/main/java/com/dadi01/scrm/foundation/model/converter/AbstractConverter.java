package com.dadi01.scrm.foundation.model.converter;

import com.dadi01.scrm.foundation.model.dto.AbstractResult;
import com.dadi01.scrm.foundation.model.dto.ResultDTO;
import com.dadi01.scrm.foundation.model.dto.ResultListDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DTO 和 BO 转换类
 *
 * @author fangming
 * @description
 * @date 2019/11/26 21:30
 */
public abstract class AbstractConverter<DTO, BO> {

    /**
     * 将DTO转换为BO
     *
     * @param dto
     * @return
     */
    public abstract BO toBO(final DTO dto);

    /**
     * 将BO转换为DTO
     * @param bo
     * @return
     */
    public abstract DTO toDTO(final BO bo);

    /**
     * 将 BO参数封装并返回
     * @param bo
     * @return
     */
    public final ResultDTO<DTO> toResultDTO(final BO bo) {
        final DTO dto = (bo == null) ? null : this.toDTO(bo);
        final ResultDTO<DTO> resultDTO = ResultDTO.success(dto);
        return resultDTO;
    }

    /**
     * 将 resultBO 封装并返回
     * @param resultBO
     * @return
     */
    public final ResultDTO<DTO> toResultDTO(final ResultDTO<BO> resultBO) {
        if(AbstractResult.Status.FAILURE.equals(resultBO.getStatus())){
            return (ResultDTO<DTO>)ResultDTO.failure(resultBO.getError());
        }
        final DTO dto = (resultBO.getData() == null) ? null : this.toDTO(resultBO.getData());
        return ResultDTO.success(dto);
    }

    /**
     * 将BOList参数封装并返回
     *
     * @param BOList
     * @return
     */
    public final ResultListDTO<DTO> toResultListDTO(final List<BO> BOList) {
        final List<DTO> dtoList = this.toListDTO(BOList);
        final ResultListDTO<DTO> resultListDTO = ResultListDTO.success(dtoList);
        return resultListDTO;
    }

    /**
     * 将查询到BO集合转换为DTO集合，封装并返回
     *
     * @param resultList
     * @return
     */
    public final ResultListDTO<DTO> toPageListDTO(final ResultListDTO<BO> resultList) {
        final List<DTO> dtoList = this.toListDTO(resultList.getData());
        final ResultListDTO<DTO> resultListDTO = ResultListDTO.success(dtoList);
        return resultListDTO;
    }

    /**
     * 将DTO集合转换为BO集合
     *
     * @param dtoList
     * @return
     */
    public final List<BO> toListBO(final List<DTO> dtoList) {
        final List<BO> BOList = new ArrayList<BO>();
        for (DTO dto : dtoList) {
            BOList.add(this.toBO(dto));
        }
        return BOList;
    }

    /**
     * 将BO集合转换为DTO集合
     *
     * @param BOList
     * @return
     */
    public final List<DTO> toListDTO(final List<BO> BOList) {
        List dtoList = new ArrayList();
        Optional.ofNullable(BOList).ifPresent(list -> list.forEach(bo -> dtoList.add(this.toDTO(bo))));
        return dtoList;
    }

}
