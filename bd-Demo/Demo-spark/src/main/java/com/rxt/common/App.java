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
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update(srcStr.getBytes("UTF-8"));
        byte[] target = md5.digest();
        System.out.println(new String(target));
        System.out.println(org.apache.commons.codec.binary.Hex.encodeHex(md5.digest()));


        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        sha1.update(srcStr.getBytes("UTF-8"));
        byte[] sha1Target = sha1.digest();
        System.out.println(new String(sha1Target));
        System.out.println(org.apache.commons.codec.binary.Hex.encodeHex(sha1.digest()));


    }
}
