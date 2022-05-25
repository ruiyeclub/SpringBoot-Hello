package cn.ruiyeclub.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ruiyeclub.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysRole)表数据库访问层
 *
 * @author Ray。
 * @since 2022-05-25 22:30:44
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    List<SysRole> findRoleByUsername(@Param("username") String username);

}

