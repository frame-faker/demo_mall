package com.sanbangzi.thriparty.wx.xcx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.util.Arrays;
import java.util.Map;

public class XcxUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(XcxUtil.class);

    /**
     * 解密微信用户信息
     */
    public static Map<String, Object> decodeUserInfo(String userInfo, String sessionKey) {
        JSONObject jsonObject = JSON.parseObject(userInfo);

        // 被加密的数据
        String encryptedData = jsonObject.getString("encryptedData");
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        String iv = jsonObject.getString("iv");
        byte[] ivByte = Base64.decode(iv);

        //如果密钥不足16位，那么就补足。备注：这个if中的内容很重要
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            //获取解密公钥KEY
            SecretKeySpec specKey = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, specKey, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);

            //返回结果
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                LOGGER.info("小程序解密用户信息为:[{}]", result);
                return JSON.parseObject(result, Map.class);
            }
        } catch (Exception ex) {
            LOGGER.error("小程序解密用户信息失败:[{}]", ex);
        }
        return null;
    }
}
