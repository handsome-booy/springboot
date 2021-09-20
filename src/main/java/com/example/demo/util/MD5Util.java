package com.example.demo.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class MD5Util {
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * 第一次md5加密
     */
    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二次md5加密
     */
    public static String formPassToDBPass(String formPass,String salt){
        String str = salt+formPass+salt;
        return DigestUtils.md5Hex(str);
    }

    /**
     * 两次MD5加密
     */
    public static String inputPassToDBPass(String inputPass,String salt){
        String s = inputPassToFormPass(inputPass);
        String s1 = formPassToDBPass(s, salt);
        return s1;
    }

    public static void main(String[] args) {
        String s1 = inputPassToDBPass("xxj123456789","salt");

        System.out.println(s1);

        String s2 = inputPassToDBPass("xxj123456789","salt");

        System.out.println(s2);
    }
}
