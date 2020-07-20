package cn.ruiyeclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (UserRecord)表控制层
 * @author Ray。
 * @data 2020-07-17 21:51:10
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
     * 分页查询所有数据
     * @param userRecord 查询实体
     * @return 所有数据
     */
    @GetMapping("page")
    public R selectAll(UserRecord userRecord) {
        //这里不写条件 默认就是1,10
        Page<UserRecord> page=new Page<>(1,3);
        Page<UserRecord> page1 = userRecordService.page(page, new QueryWrapper<>(userRecord));
        return success(page1);
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(userRecordService.getById(id));
    }

    /**
     * 新增数据
     * @param userRecord 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public R insert(@RequestBody UserRecord userRecord) {
        return success(userRecordService.save(userRecord));
    }

    /**
     * 修改数据
     * @param userRecord 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public R update(@RequestBody UserRecord userRecord) {
        return success(userRecordService.updateById(userRecord));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(userRecordService.removeByIds(idList));
    }
}