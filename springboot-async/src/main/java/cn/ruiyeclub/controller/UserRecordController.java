package cn.ruiyeclub.controller;

import cn.ruiyeclub.service.UserRecordService;
import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserRecord)表控制层
 *
 * @author Ray。
 * @date 2020-07-22 14:06:53
 */
@RestController
@RequestMapping("userRecord")
public class UserRecordController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private UserRecordService userRecordService;

    /**
     * 保存方法
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("save")
    public int save() throws InterruptedException {
        return userRecordService.execute();
    }

}
