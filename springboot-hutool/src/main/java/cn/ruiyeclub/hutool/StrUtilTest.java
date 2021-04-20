package cn.ruiyeclub.hutool;

import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类，定义了一些常用的字符串操作方法。StrUtil比StringUtil名称更短，用起来也更方便！
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class StrUtilTest {

    public static void main(String[] args) {
        //判断是否为空字符串
        String str = "test";
        StrUtil.isEmpty(str);
        StrUtil.isNotEmpty(str);

        //去除字符串的前后缀
        System.out.println(StrUtil.removeSuffix("a.jpg", ".jpg"));
        System.out.println(StrUtil.removePrefix("a.jpg", "a."));

        //格式化字符串
        String template = "这只是个占位符:{}";
        String str2 = StrUtil.format(template, "我是占位符");
        System.out.println("strUtil format:"+str2);

    }
}
