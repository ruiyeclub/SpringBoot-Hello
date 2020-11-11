package cn.ruiyeclub.utils;

import java.security.MessageDigest;
import java.util.Random;

public class PayUtils {


    /**
     * 生成随机字符串nonce_str
     */
    public static String get_nonce_str() {
        String base   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random   = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    public static String MD5(String data){
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }
    
    public static void main(String[] args) {
        System.out.println(get_nonce_str().length());
    }

}