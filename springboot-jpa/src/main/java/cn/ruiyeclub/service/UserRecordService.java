package cn.ruiyeclub.service;

import cn.ruiyeclub.entity.UserRecord;

import java.util.List;

/**
 * (UserRecord)表服务接口
 *
 * @author Ray。
 * @date 2020-07-22 14:06:52
 */
public interface UserRecordService {

    List<UserRecord> findAll();

    UserRecord getUser(Integer id);
}