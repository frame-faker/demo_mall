package com.sanbangzi.common.utils;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class BeanUtil extends BeanUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 对象拷贝 (数据源为空返回空)
     * @param from 数据源
     * @param to 目标对象
     * @param <T> 范型
     * @return 数据源为空返回空
     * @throws Exception
     */
    public static <T> T copyObject(Object from, Class to) {
        Object dest = null;
        try {
            if (null == from) {
                return null;
            }
            dest = to.newInstance();
            copyProperties(from, dest);
        } catch (Exception e) {
            LOGGER.error("拷贝对象错误:" + e);
        }
        return (T) dest;
    }

    /**
     * 拷贝集合
     * @param from 数据源
     * @param to 目标
     * @param <T> 范型
     * @return 数据源为空不返回空
     */
    public static <T> List<T> copyList(List from, Class to) {
        if (null == from || from.isEmpty()) {
            return Lists.newArrayList();
        }
        List<T> dest = Lists.newArrayList();
        from.forEach((Object e) -> {
            try {
                T toObject = (T) to.newInstance();
                copyProperties(e, toObject);
                dest.add(toObject);
            } catch (Exception e1) {
                LOGGER.error("拷贝集合错误:" + e1);
            }
        });
        return dest;
    }

}
