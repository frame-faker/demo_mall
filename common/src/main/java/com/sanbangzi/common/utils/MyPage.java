package com.sanbangzi.common.utils;


import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPage<T> {

    // 页数
    private Integer pageNum;

    // 每页大小
    private Integer pageSize;

    // 总页数
    private Integer pages;

    // 总记录数
    private Long total;

    // 数据
    private List<T> data;

    public static MyPage newInstance(PageInfo pageInfo) {
        return new MyPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }
}
