package cn.ruiyeclub.controller;


import cn.ruiyeclub.entity.Dept;
import cn.ruiyeclub.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author Ray。
 * @since 2020-10-19 16:43:44
 */
@RestController
@RequestMapping("dept")
public class DeptController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param dept 查询实体
     * @return 所有数据
     */
    @GetMapping("page")
    public R selectAll(Page<Dept> page, Dept dept) {
        return success(this.deptService.page(page, new QueryWrapper<>(dept)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.deptService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dept 实体对象
     * @return 新增结果
     */
    @PostMapping("save")
    public R insert(@RequestBody Dept dept) {
        return success(this.deptService.save(dept));
    }

    /**
     * 修改数据
     *
     * @param dept 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public R update(@RequestBody Dept dept) {
        return success(this.deptService.updateById(dept));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("del")
    public R delete(@RequestParam("id") Long id) {
        return success(this.deptService.removeById(id));
    }

    @GetMapping("findAll")
    public List<Dept> findAll() {
        return deptService.list();
    }
}
