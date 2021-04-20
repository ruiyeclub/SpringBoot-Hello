package cn.ruiyeclub.hutool;

import cn.hutool.core.util.ReflectUtil;
import cn.ruiyeclub.entity.PmsBrand;

import java.lang.reflect.Method;

/**
 * Java反射工具类，可用于反射获取类的方法及创建对象。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class ReflectUtilTest {

    public static void main(String[] args) {
        //获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(PmsBrand.class);
        //获取某个类的指定方法
        Method method = ReflectUtil.getMethod(PmsBrand.class, "getId");
        //使用反射来创建对象
        PmsBrand pmsBrand = ReflectUtil.newInstance(PmsBrand.class);
        //反射执行对象的方法
        ReflectUtil.invoke(pmsBrand, "setId", 1);

    }
}
