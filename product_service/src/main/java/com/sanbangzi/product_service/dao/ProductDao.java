package com.sanbangzi.product_service.dao;

import com.sanbangzi.common.utils.MyMapper;
import com.sanbangzi.domain.dto.res.ProductResDTO;
import com.sanbangzi.domain.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao extends MyMapper<ProductEntity> {

    List<ProductResDTO> pageByType(@Param("typeId") Long typeId);

}
