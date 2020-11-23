package com.boxuegu.basis.pulsar.qimoor.utils;

/**
 * @description: 验证字符串是否是乱码
 * @author: renyu
 * @create: 2019-07-29 14:47
 **/
public class MessyCodeCheckUtil {

    /**
     * 判断字符串是否是乱码
     * @return true-是乱码
     */
    public static boolean isMessyCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
            //从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
            //System.out.println("--- " + (int) c);
            if ((int) c == 0xfffd) {
                // 存在乱码
                //System.out.println("存在乱码 " + (int) c);
                return true;
            }
        }
        return false;
    }
}
