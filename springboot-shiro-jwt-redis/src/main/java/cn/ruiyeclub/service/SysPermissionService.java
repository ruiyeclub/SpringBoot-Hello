package cn.ruiyeclub.service;

import cn.ruiyeclub.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysPermission)表服务接口
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据角色ID查询角色对应的权限信息
     */
    List<SysPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);
}

