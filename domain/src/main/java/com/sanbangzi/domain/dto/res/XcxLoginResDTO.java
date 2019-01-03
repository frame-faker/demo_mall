package com.sanbangzi.domain.dto.res;

import lombok.Data;

@Data
public class XcxLoginResDTO {

    /** 用户token */
    private String accessToken;

    /** 用户id */
    private Long userId;

    /** 用户昵称 */
    private String nickName;

    /** 用户头像 */
    private String headPhoto;
}
