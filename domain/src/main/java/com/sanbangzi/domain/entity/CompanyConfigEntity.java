package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 公司配置表 */
@Data
@Table(name = "company_config")
public class CompanyConfigEntity {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 键 */
    private String name;

    /** 值 */
    private String value;

    /** 描述 */
    private String description;

    /** 公司id(关联company.id) */
    private Long companyId;

}
