package com.sanbangzi.domain.dto.res;

import lombok.Data;

@Data
public class ProductResDTO {

    /** 主键 */
    private Long id;

    /** 主图 */
    private String photo;

    /** 标题 */
    private String title;

    /** 产品价格(分) */
    private Integer price;
}
