package com.sanbangzi.xcx_web.api;

import com.sanbangzi.common.utils.WebResponse;

public abstract class BaseController {

    /**
     * 成功返回不带数据
     */
    protected WebResponse xcxSuccess() {
        WebResponse response = new WebResponse();
        response.setCode(200);
        response.setMsg("成功");
        return response;
    }

    /**
     * 成功返回带数据
     */
    protected WebResponse xcxSuccess(Object data) {
        return new WebResponse(200, "成功", data);
    }

}
