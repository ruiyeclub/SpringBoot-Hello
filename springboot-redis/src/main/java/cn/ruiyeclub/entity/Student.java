package cn.ruiyeclub.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 实现序列化接口才可以存入redis
 */
@Data
public class Student implements Serializable {

    private Integer id;
    private String name;
    private Double score;
    private Date birthday;
}
