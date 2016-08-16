package com.github.izhangzhihao.SpringMVCSeedProject.Utils;


import java.util.UUID;

public class StringUtils {
    /**
     * 是Null或""吗？
     *
     * @param value 需要判断的对象
     * @return 是Null或""吗？
     */
    public static boolean isNullOrEmpty(final Object value) {
        if (value instanceof String)
            return "".equals(value);
        return value == null;
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
