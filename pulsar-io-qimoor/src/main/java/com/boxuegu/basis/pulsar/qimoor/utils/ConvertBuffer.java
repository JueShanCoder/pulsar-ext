package com.boxuegu.basis.pulsar.qimoor.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author : Liuzl
 * @date: 2020/6/30  4:55 下午
 * @description: ByteBuffer 、 String 转换器
 */
public class ConvertBuffer {

    public static ByteBuffer string2ByteBuffer(String s, Charset charset) {
        return ByteBuffer.wrap(s.getBytes(charset));
    }

    public static String byteBuffer2String(ByteBuffer buf, Charset charset) {
        byte[] bytes;
        if (buf.hasArray()) {
            bytes = buf.array();
        } else {
            buf.rewind();
            bytes = new byte[buf.remaining()];
        }
        return new String(bytes, charset);
    }
}