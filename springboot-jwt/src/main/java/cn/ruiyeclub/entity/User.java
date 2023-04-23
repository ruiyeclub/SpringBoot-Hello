package cn.ruiyeclub.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author Ray。
 * @since 2020-05-24 11:00:59
 */
public class User implements Serializable {
    private static final long serialVersionUID = -73563816954561803L;

    private Integer id;

    private String name;

    private String password;

    private String perms;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", perms='" + perms + '\'' +
                '}';
    }
}
