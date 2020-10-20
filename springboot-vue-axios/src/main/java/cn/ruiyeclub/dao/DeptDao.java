package cn.ruiyeclub.dao;

import cn.ruiyeclub.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表(Dept)表数据库访问层
 *
 * @author Ray。
 * @since 2020-10-19 16:43:43
 */
@Mapper
public interface DeptDao extends BaseMapper<Dept> {

}