package cn.ruiyeclub.service;

import cn.ruiyeclub.pojo.User;

/**
 * @author Ray。
 * @create 2020-03-11 21:18
 */
public interface UserService {
    public User queryUserByName(String name);
}