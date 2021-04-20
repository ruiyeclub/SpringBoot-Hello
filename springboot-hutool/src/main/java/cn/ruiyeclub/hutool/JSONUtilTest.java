package cn.ruiyeclub.hutool;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import cn.ruiyeclub.entity.PmsBrand;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析工具类，可用于对象与JSON之间的互相转化。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class JSONUtilTest {

    public static void main(String[] args) {
        PmsBrand brand = new PmsBrand();
        brand.setId(1);
        brand.setName("小米");
        brand.setShowStatus(1);
        //对象转化为JSON字符串
        String jsonStr = JSONUtil.parse(brand).toString();
        System.out.println("jsonUtil parse:"+jsonStr);

        //JSON字符串转化为对象
        PmsBrand brandBean = JSONUtil.toBean(jsonStr, PmsBrand.class);
        System.out.println("jsonUtil toBean:"+brandBean);
        List<PmsBrand> brandList = new ArrayList<PmsBrand>();
        brandList.add(brand);
        String jsonListStr = JSONUtil.parse(brandList).toString();

        //JSON字符串转化为列表
        brandList = JSONUtil.toList(new JSONArray(jsonListStr), PmsBrand.class);
        System.out.println("jsonUtil toList:"+brandList);
    }
}
