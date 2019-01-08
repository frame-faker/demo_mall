package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/** 产品表 */
@Data
@Table(name = "product")
public class ProductEntity {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 产品唯一码 */
    private String code;

    /** 主图 */
    private String photo;

    /** 标题 */
    private String title;

    /** 产品价格(分) */
    private Integer price;

    /** 状态(1:正常) */
    private Byte status;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 公司id(关联company.id) */
    private Long companyId;

}
