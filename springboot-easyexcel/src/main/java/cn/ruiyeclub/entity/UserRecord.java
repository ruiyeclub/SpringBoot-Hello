package cn.ruiyeclub.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (UserRecord)表实体类
 * @author Ray。
 * @date 2020-07-28 11:14:05
 */
public class UserRecord extends Model<UserRecord> {

    private Integer id;

    private String name;

    private Integer age;

    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}