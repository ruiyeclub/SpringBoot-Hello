package cn.ruiyeclub.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 集合操作的工具类，定义了一些常用的集合操作。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class CollUtilTest {

    public static void main(String[] args) {
        //数组转换为列表
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        List<String> list = CollUtil.newArrayList(array);

        //join：数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        System.out.println("collUtil join:"+joinStr);

        //将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        System.out.println("collUtil split:"+splitList);

        //创建新的Map、Set、List
        HashMap<Object, Object> newMap = CollUtil.newHashMap();
        HashSet<Object> newHashSet = CollUtil.newHashSet();
        ArrayList<Object> newList = CollUtil.newArrayList();
        //判断列表是否为空
        CollUtil.isEmpty(list);

    }
}
