package cn.ruiyeclub.service;

import cn.ruiyeclub.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (SysRole)表服务接口
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> findRoleByUsername(String username);
}

