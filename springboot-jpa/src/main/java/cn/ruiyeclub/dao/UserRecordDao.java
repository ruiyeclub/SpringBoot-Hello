package cn.ruiyeclub.dao;

import cn.ruiyeclub.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * (UserRecord)表数据库访问层
 *
 * @author Ray。
 * @date 2020-07-22 14:06:51
 */
@Repository
public interface UserRecordDao extends JpaRepository<UserRecord,Integer> {

    @Query(value = "select * from user_record where id =:id ", nativeQuery = true)
    UserRecord getUser(@Param("id") Integer id);

    /**
     * 根据年纪查询用户
     * @param age
     * @return
     */
    UserRecord findByAge(Integer age);

    /**
     * 根据年纪和姓名查询
     * @param name
     * @param age
     * @return
     */
    UserRecord findByNameAndAge(String name, Integer age);
}