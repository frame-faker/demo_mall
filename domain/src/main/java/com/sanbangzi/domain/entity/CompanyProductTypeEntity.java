package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/** 公司产品分类 */
@Data
@Table(name = "company_product_type")
public class CompanyProductTypeEntity {

    /** 主键 */
    @Id
    private Long id;

    /** 名称 */
    private String name;

    /** 排序 */
    private Byte sort;

    /** 公司id(关联company.id) */
    private Long companyId;

}
