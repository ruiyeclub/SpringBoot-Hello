package cn.ruiyeclub.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.ruiyeclub.entity.PmsBrand;

import java.util.Map;

/**
 * JavaBean工具类，可用于Map与JavaBean对象的互相转换以及对象属性的拷贝。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class BeanUtilTest {

    public static void main(String[] args) {
        PmsBrand brand = new PmsBrand();
        brand.setId(1);
        brand.setName("小米");
        brand.setShowStatus(0);

        //Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(brand);
        System.out.println("beanUtil bean to map:"+map);

        //Map转Bean
        PmsBrand mapBrand = BeanUtil.mapToBean(map, PmsBrand.class, false);
        System.out.println("beanUtil map to bean:"+mapBrand);
        
        //Bean属性拷贝
        PmsBrand copyBrand = new PmsBrand();
        BeanUtil.copyProperties(brand, copyBrand);
        System.out.println("beanUtil copy properties:"+copyBrand);
    }
}
