package com.rxt.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String srcStr = "中华人民共和国";
        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(srcStr.getBytes("UTF-8"));
        byte[] target = md.digest();
        System.out.println(new String(target));
        System.out.println(org.apache.commons.codec.binary.Hex.encodeHex(md.digest()));

    }
}
