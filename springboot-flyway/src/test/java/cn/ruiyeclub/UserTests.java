package cn.ruiyeclub;

import cn.ruiyeclub.entity.User;
import cn.ruiyeclub.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: Ray。
 * @Date: 2021/1/20
 */
@Slf4j
@SpringBootTest
public class UserTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() throws Exception {
        // 插入2个用户
        userMapper.insert(new User(1L, "Tom", 10));
        userMapper.insert(new User(2L, "Mike", 11));

        // 查数据库，应该有5个用户
        userMapper.selectList(new LambdaQueryWrapper<>());
    }

}