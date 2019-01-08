package com.sanbangzi.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/** 用户表 */
@Data
@Table(name = "user")
public class UserEntity {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户唯一码 */
    private String code;

    /** 小程序opendId */
    private String openId;

    /** 昵称 */
    private String nickName;

    /** 头像 */
    private String headPhoto;

    /** 状态(1:正常) */
    private Byte status;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

}
