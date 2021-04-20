package cn.ruiyeclub.hutool;

import cn.hutool.core.convert.Convert;

import java.util.Date;
import java.util.List;

/**
 * 类型转换工具类，用于各种类型数据的转换。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class ConvertTest {

    public static void main(String[] args) {
        //转换为字符串
        int a = 1;
        String aStr = Convert.toStr(a);

        //转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);

        //转换为日期对象
        String dateStr = "2017-05-06";
        Date date = Convert.toDate(dateStr);

        //转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        List<String> strList = Convert.toList(String.class, strArr);
    }
}
