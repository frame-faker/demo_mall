package com.sanbangzi.domain.dto.req;

import lombok.Data;

@Data
public class UserReqDTO {

    /** 小程序opendId */
    private String openId;

    /** 昵称 */
    private String nickName;

    /** 头像 */
    private String headPhoto;
}
