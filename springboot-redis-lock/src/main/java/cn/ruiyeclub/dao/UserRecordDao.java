package cn.ruiyeclub.dao;

import cn.ruiyeclub.entity.UserRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * (UserRecord)表数据库访问层
 *
 * @author Ray。
 * @since 2020-10-16 16:29:27
 */
@Mapper
public interface UserRecordDao extends BaseMapper<UserRecord> {

    @Update("update user_record set age=age-1 where id=#{id}")
    int updateAgeById(Integer id);

}