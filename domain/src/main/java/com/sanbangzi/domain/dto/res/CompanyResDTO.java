package com.sanbangzi.domain.dto.res;

import lombok.Data;

@Data
public class CompanyResDTO {

    /** 主键 */
    private Long id;

    /** 公司唯一码 */
    private String code;

    /** 名称 */
    private String name;

    /** 客服电话 */
    private String hotline;
}
