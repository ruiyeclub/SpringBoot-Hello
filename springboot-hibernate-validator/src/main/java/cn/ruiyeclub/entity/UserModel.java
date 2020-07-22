package cn.ruiyeclub.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description:
 * @author: Ray。
 * @create: 2020-01-17 15:17
 */
@Data
public class UserModel {

    @NotNull(message = "用户名称不能为空！")
    private String userName;

    @NotNull(message = "age不能为null!")
    @Range(min = 1, max = 888, message = "范围为1至888")
    private Integer age;

    /**
     * 日期格式化转换
     */
    @NotNull(message = "日期不能为null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

}
