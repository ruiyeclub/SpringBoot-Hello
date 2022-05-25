package cn.ruiyeclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ruiyeclub.dao.SysUserDao;
import cn.ruiyeclub.entity.SysUser;
import cn.ruiyeclub.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * (SysUser)表服务实现类
 *
 * @author Ray。
 * @since 2022-05-25 20:09:43
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}

