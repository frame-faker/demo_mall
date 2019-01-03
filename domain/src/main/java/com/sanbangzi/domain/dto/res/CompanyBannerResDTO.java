package com.sanbangzi.domain.dto.res;

import lombok.Data;

@Data
public class CompanyBannerResDTO {

    /** 图片 */
    private String picture;

    /** 链接 */
    private String link;

    /** 类型(1:无链接,2:产品) */
    private Byte type;
}
