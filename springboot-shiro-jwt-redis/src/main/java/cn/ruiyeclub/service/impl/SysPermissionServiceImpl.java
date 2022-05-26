package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.dao.SysPermissionDao;
import cn.ruiyeclub.entity.SysPermission;
import cn.ruiyeclub.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysPermission)表服务实现类
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

    @Resource
    private SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> findPermissionByRoleId(Integer roleId) {
        return sysPermissionDao.findPermissionByRoleId(roleId);
    }
}

