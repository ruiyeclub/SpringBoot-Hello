package cn.ruiyeclub.hutool;

import cn.hutool.crypto.SecureUtil;

/**
 * 加密解密工具类，可用于MD5加密。
 * @Author: Ray。
 * @Date: 2021/4/20
 */
public class SecureUtilTest {

    public static void main(String[] args) {
        //MD5加密
        String str = "123456";
        String md5Str = SecureUtil.md5(str);
        System.out.println("secureUtil md5:"+md5Str);
    }
}
