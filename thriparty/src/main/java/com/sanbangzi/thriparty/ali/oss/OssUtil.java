package com.sanbangzi.thriparty.ali.oss;

import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.ServerException;

public class OssUtil {

    /**
     * 字节数组上传
     */
    public static String uploadByteArr(String key, byte[] bytes) throws ServerException {
        OSSClient ossClient = new OSSClient(OssConst.ENDPOINT, OssConst.ACCESS_KEY_ID, OssConst.ACCESS_KEY_SECRET);
        ossClient.putObject(OssConst.BUCKET, key, new ByteArrayInputStream(bytes));
        ossClient.shutdown();
        return OssConst.FILE_NAME_PREFIX + key;
    }

    /**
     * 图片地址上传
     */
    public static String uploadUrlImg(String key, String imgUrl) throws ServerException {
        OSSClient ossClient = new OSSClient(OssConst.ENDPOINT, OssConst.ACCESS_KEY_ID, OssConst.ACCESS_KEY_SECRET);
        InputStream inputStream = null;
        try {
            inputStream = new URL(imgUrl).openStream();
            ossClient.putObject(OssConst.BUCKET, key, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return OssConst.FILE_NAME_PREFIX + key;
    }

}
