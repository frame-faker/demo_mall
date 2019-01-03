package com.sanbangzi.domain.dto.req;

import lombok.Data;

@Data
public class ProductReqDTO {

    private Long companyId;

    private Long typeId;

    private String title;

    private Integer pageNum;

    private Integer pageSize;
}
