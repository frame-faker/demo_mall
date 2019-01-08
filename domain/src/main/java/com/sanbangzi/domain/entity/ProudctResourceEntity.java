package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 产品资源表 */
@Data
@Table(name = "proudct_resource")
public class ProudctResourceEntity {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 资源 */
    private String resource;

    /** 资源类型(1:轮播图,2:产品详情) */
    private Byte type;

    /** 排序 */
    private Byte sort;

    /** 产品id(关联product.id) */
    private Long productId;

}
