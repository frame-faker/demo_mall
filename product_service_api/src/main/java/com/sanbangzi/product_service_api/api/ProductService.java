package com.sanbangzi.product_service_api.api;

import com.sanbangzi.common.utils.MyPage;
import com.sanbangzi.domain.dto.req.ProductReqDTO;
import com.sanbangzi.domain.dto.res.ProductDetailResDTO;
import com.sanbangzi.domain.dto.res.ProductResDTO;
import com.sanbangzi.product_service_api.exception.ProductException;

import java.util.List;

public interface ProductService {

    List<ProductResDTO> getNew(Long companyId) throws ProductException;

    MyPage<ProductResDTO> pageRecommend(ProductReqDTO productReqDTO) throws ProductException;

    MyPage<ProductResDTO> searchByTitle(ProductReqDTO productReqDTO) throws ProductException;

    MyPage<ProductResDTO> pageByType(ProductReqDTO productReqDTO) throws ProductException;

    ProductDetailResDTO getDetailById(Long productId) throws Exception;
}
