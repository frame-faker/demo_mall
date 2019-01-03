package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/** 公司账号表 */
@Data
@Table(name = "company_account")
public class CompanyAccountEntity {

    /** 主键 */
    @Id
    private Long id;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 头像 */
    private String headPhoto;

    /** 昵称 */
    private String nickName;

    /** 状态(1:正常) */
    private Byte status;

    /** 创建时间 */
    private Date createTime;

    /** 公司id(关联company.id) */
    private Long companyId;

}
