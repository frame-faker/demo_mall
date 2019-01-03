package com.sanbangzi.common.utils;

import java.util.UUID;

public class RandomUtil {

    /**
     * 32位uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toString();
    }
}
