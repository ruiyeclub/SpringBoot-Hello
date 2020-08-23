package cn.ruiyeclub.controller;


import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserRecord)表控制层
 * @author Ray。
 * @date 2020-07-22 14:06:53
 */
@RestController
@RequestMapping("user")
public class UserRecordController{
    /**
     * 服务对象
     */
    @Resource
    private UserRecordService userRecordService;

    /**
     * 查询所有数据
     * @return 所有数据
     */
    @GetMapping("/list")
    public List<UserRecord> findAll() {
        return userRecordService.findAll();
    }

    @GetMapping("/{id}")
    public UserRecord getUser(@PathVariable("id") Integer id){
        return userRecordService.getUser(id);
    }
}