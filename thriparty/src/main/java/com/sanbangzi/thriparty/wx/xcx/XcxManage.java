package com.sanbangzi.thriparty.wx.xcx;

import java.util.Map;

public interface XcxManage {

    /**
     * 根据code兑换openId
     * @param code
     * @return
     */
    Map<String, String> getOpenIdByCode(String code);
}
