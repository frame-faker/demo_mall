package com.sanbangzi.domain.dto.res;

import lombok.Data;

@Data
public class UserResDTO {

    /** 用户id */
    private Long id;

    /** 用户唯一码 */
    private String code;

    /** 小程序opendId */
    private String openId;

    /** 昵称 */
    private String nickName;

    /** 头像 */
    private String headPhoto;
}
