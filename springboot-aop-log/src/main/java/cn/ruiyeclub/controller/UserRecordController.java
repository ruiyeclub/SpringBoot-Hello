package cn.ruiyeclub.controller;

import cn.ruiyeclub.annotation.Log;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserRecord)表控制层
 *
 * @author Ray。
 * @since 2020-07-22 14:29:23
 */
@RestController
@RequestMapping("userRecord")
public class UserRecordController {
    /**
     * 服务对象
     */
    @Resource
    private UserRecordService userRecordService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Log("查看用户")
    @GetMapping("selectOne")
    public UserRecord selectOne(Integer id) {
        return this.userRecordService.queryById(id);
    }

}