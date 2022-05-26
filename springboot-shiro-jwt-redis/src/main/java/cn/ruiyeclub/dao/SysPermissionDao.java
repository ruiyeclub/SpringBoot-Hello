package cn.ruiyeclub.dao;

import cn.ruiyeclub.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysPermission)表数据库访问层
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
public interface SysPermissionDao extends BaseMapper<SysPermission> {

    List<SysPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);

}

