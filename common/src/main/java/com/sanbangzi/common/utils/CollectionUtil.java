package com.sanbangzi.common.utils;

import java.util.Collection;

public class CollectionUtil {

    public static boolean isBlank(Collection c) {
        return null == c || c.isEmpty();
    }

    public static boolean isNotBlank(Collection c) {
        return !isBlank(c);
    }
}
