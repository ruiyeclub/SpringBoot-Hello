package cn.ruiyeclub.controller;

import cn.ruiyeclub.entity.SysLog;
import cn.ruiyeclub.service.SysLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysLog)表控制层
 *
 * @author Ray。
 * @since 2020-06-07 22:04:11
 */
@RestController
@RequestMapping("sysLog")
public class SysLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogService sysLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysLog selectOne(Integer id) {
        return this.sysLogService.queryById(id);
    }

}