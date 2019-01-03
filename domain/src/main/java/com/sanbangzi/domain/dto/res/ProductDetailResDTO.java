package com.sanbangzi.domain.dto.res;

import lombok.Data;

@Data
public class ProductDetailResDTO {

    /** 主键 */
    private Long id;

    /** 标题 */
    private String title;

    /** 产品价格(分) */
    private Integer price;

    /** 轮播数据 */
    private String carousel;

    /** 产品详情 */
    private String detail;
}
