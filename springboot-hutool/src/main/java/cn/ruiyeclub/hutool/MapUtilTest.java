package cn.ruiyeclub.hutool;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * Map操作工具类，可用于创建Map对象及判断Map是否为空。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class MapUtilTest {

    public static void main(String[] args) {
        //将多个键值对加入到Map中
        Map<Object, Object> map = MapUtil.of(new String[][]{
                {"key1", "value1"},
                {"key2", "value2"},
                {"key3", "value3"}
        });

        //判断Map是否为空
        System.out.println(MapUtil.isEmpty(map));
        MapUtil.isNotEmpty(map);

    }
}
