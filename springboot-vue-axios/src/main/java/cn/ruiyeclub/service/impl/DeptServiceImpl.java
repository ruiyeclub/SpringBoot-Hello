package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.dao.DeptDao;
import cn.ruiyeclub.entity.Dept;
import cn.ruiyeclub.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author Ray。
 * @since 2020-10-19 16:43:44
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

}