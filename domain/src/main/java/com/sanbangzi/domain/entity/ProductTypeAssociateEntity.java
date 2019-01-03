package com.sanbangzi.domain.entity;

import lombok.Data;

/** 产品分类关系表 */
@Data
public class ProductTypeAssociateEntity {

    /** 产品id(关联product.id) */
    private Long productId;

    /** 公司产品分类id(关联company_product_type.id) */
    private Long companyProductTypeId;

}
