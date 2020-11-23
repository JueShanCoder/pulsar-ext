package com.boxuegu.basis.pulsar.qimoor.utils;


/**
 * @author : Liuzl
 * @Date: 2020/8/6  11:17 上午
 * @description:
 */
public class StringUtils {
    private static boolean containsText( CharSequence str) {
        int strLen = str.length();

        for(int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasLength( CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean isEmpty( Object str) {
        return str == null || "".equals(str);
    }

    public static boolean hasText( String str) {
        return str != null && !str.isEmpty() && containsText(str);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

}