/**
 * FileName: BufferDemo
 * Author:   Ren Xiaotian
 * Date:     2018/11/7 20:31
 */

package com.rxt.common.nio.buffer;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Buffer 编解码Demo
 */
public class BufferDemo {
    /**
     * 数据编码
     *
     * @param str        带编码的字符串
     * @param charEncode 指定编码格式
     * @return 返回指定编码后的byte[]
     */
    public static byte[] encode(String str, String charEncode) {
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(str);
        charBuffer.flip();

        //获取指定的编解码器
        Charset charset = Charset.forName(charEncode);
        ByteBuffer byteBuffer = charset.encode(charBuffer);

        byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        return bytes;
    }

    /**
     * 数据解码
     *
     * @param bytes      待解码的byte[]
     * @param charEncode 指定编码格式
     * @throws UnsupportedEncodingException
     */
    public static void decode(byte[] bytes, String charEncode) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(bytes);
        byteBuffer.flip();

        Charset charset = Charset.forName(charEncode);
        CharBuffer charBuffer = charset.decode(byteBuffer);    //对bytebuffer的内容解码

        char[] charArr = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(charArr);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = encode("大数据", "UTF-8");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println();
        decode(bytes, "UTF-8");
    }

}
