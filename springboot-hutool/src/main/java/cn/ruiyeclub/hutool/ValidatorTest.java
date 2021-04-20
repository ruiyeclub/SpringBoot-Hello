package cn.ruiyeclub.hutool;

import cn.hutool.core.lang.Validator;

/**
 * 字段验证器，可以对不同格式的字符串进行验证，比如邮箱、手机号、IP等格式。
 * @Author: Ray。
 * @Date: 2021/4/20
 */
public class ValidatorTest {

    public static void main(String[] args) {
        //判断是否为邮箱地址
        boolean result = Validator.isEmail("macro@qq.com");
        System.out.println("Validator isEmail:"+result);
        //判断是否为手机号码
        result = Validator.isMobile("18911111111");
        System.out.println("Validator isMobile:"+result);
        //判断是否为IPV4地址
        result = Validator.isIpv4("192.168.3.101");
        System.out.println("Validator isIpv4:"+result);
        //判断是否为汉字
        result = Validator.isChinese("你好");
        System.out.println("Validator isChinese:"+result);
        //判断是否为身份证号码（18位中国）
        result = Validator.isCitizenId("123456");
        System.out.println("Validator isCitizenId:"+result);
        //判断是否为URL
        result = Validator.isUrl("http://www.baidu.com");
        System.out.println("Validator isUrl:"+result);
        //判断是否为生日
        result = Validator.isBirthday("2020-02-01");
        System.out.println("Validator isBirthday:"+result);

    }
}
