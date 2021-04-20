package cn.ruiyeclub.hutool;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 摘要算法工具类，支持MD5、SHA-256、Bcrypt等算法。
 * @Author: Ray。
 * @Date: 2021/4/20
 */
public class DigestUtilTest {

    public static void main(String[] args) {
        String password = "123456";
        //计算MD5摘要值，并转为16进制字符串
        String result = DigestUtil.md5Hex(password);
        System.out.println("DigestUtil md5Hex:"+result);

        //计算SHA-256摘要值，并转为16进制字符串
        result = DigestUtil.sha256Hex(password);
        System.out.println("DigestUtil sha256Hex:"+result);

        //生成Bcrypt加密后的密文，并校验
        String hashPwd = DigestUtil.bcrypt(password);
        boolean check = DigestUtil.bcryptCheck(password,hashPwd);
        System.out.println("DigestUtil bcryptCheck:"+check);

    }
}
