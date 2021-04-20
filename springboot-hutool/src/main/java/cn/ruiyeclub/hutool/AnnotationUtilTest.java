package cn.ruiyeclub.hutool;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;


/**
 * 注解工具类，可用于获取注解与注解中指定的值。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class AnnotationUtilTest {

    public static void main(String[] args) {
        String testStr = "test中文";

// 此处密钥如果有非ASCII字符，考虑编码
        byte[] key = "password".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);

// b977f4b13f93f549e06140971bded384
        String macHex1 = mac.digestHex(testStr);
    }
}
