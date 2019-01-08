package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/** 公司表 */
@Data
@Table(name = "company")
public class CompanyEntity {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 公司唯一码 */
    private String code;

    /** 名称 */
    private String name;

    /** 联系人 */
    private String linkMan;

    /** 联系电话 */
    private String linkPhone;

    /** 客服电话 */
    private String hotline;

    /** 状态(1:正常) */
    private Byte status;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

}
