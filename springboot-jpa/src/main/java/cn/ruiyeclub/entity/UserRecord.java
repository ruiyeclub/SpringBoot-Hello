package cn.ruiyeclub.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * (UserRecord)表实体类
 *
 * @author Ray。
 * @date 2020-07-22 14:06:49
 */
@Data
@Entity
public class UserRecord{

    /**
     * 声明id
     */
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    private String email;
}