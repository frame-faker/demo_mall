package com.sanbangzi.thriparty.wx.xcx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class XcxUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(XcxUtil.class);

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static Map<String, Object> decrypt(String appId, String userInfo, String sessionKey){

        JSONObject params = JSON.parseObject(userInfo);
        String encryptedData = params.getString("encryptedData");
        String iv = params.getString("iv");

        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            LOGGER.error("解密微信用户信息失败,[{}]", e);
        }
        return JSON.parseObject(result, Map.class);
    }
}
