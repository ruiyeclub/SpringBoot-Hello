package cn.ruiyeclub.controller;

import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Ray。
 * @Date: 2020/10/16
 */
@RestController
public class RedisController {

    @Resource
    private UserRecordService userRecordService;

    @GetMapping("testLock/{id}")
    public UserRecord testLock(@PathVariable("id") Integer id) throws InterruptedException {
        UserRecord user = new UserRecord();
        user.setId(id);
        userRecordService.updateAgeById(user);
        Thread.sleep(8000);
        System.out.println("更新用户年龄成功");

        UserRecord userRecord = userRecordService.getById(user);
        return userRecord;

    }
}
