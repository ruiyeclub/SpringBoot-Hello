package cn.ruiyeclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ruiyeclub.dao.SysRoleDao;
import cn.ruiyeclub.entity.SysRole;
import cn.ruiyeclub.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysRole)表服务实现类
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> findRoleByUsername(String username) {
        return sysRoleDao.findRoleByUsername(username);
    }
}

