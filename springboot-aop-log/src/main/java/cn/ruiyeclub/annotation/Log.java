package cn.ruiyeclub.annotation;

import java.lang.annotation.*;

/**
 * @author Ray。
 * @create 2020-06-07 22:06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //生成文档
public @interface Log {
    //日志操作名称
    String value() default "";
    //日志级别(暂未用)
    String level() default "";
}