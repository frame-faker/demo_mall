package com.sanbangzi.thriparty.wx.xcx.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sanbangzi.common.utils.okhttp.OkHttpUtil;
import com.sanbangzi.thriparty.wx.xcx.XcxManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class XcxManageImpl implements XcxManage {

    private static final Logger LOGGER = LoggerFactory.getLogger(XcxManageImpl.class);

    private static final String APP_ID = "<your app id>";

    private static final String APP_SECRET = "<your app secret>";

    private static final String XCX_EXCHANGE_OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public Map<String, String> getOpenIdByCode(String code) {
        Map<String, String> params = Maps.newHashMap();
        params.put("grant_type", "authorization_code");
        params.put("appid", APP_ID);
        params.put("secret", APP_SECRET);
        params.put("js_code", code);
        LOGGER.info("请求微信用code兑换openId,参数[{}]", params);
        String response = OkHttpUtil.okHttpSynGet(XCX_EXCHANGE_OPEN_ID_URL, params);
        LOGGER.info("请求微信用code兑换openId,返回结果[{}]", response);
        return JSONObject.parseObject(response, Map.class);
    }

}
