package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.dao.UserRecordDao;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserRecord)表服务实现类
 *
 * @author Ray。
 * @date 2020-07-22 14:06:53
 */
@Service
public class UserRecordServiceImpl implements UserRecordService {

    @Resource
    private UserRecordDao userRecordDao;

    @Override
    public List<UserRecord> findAll() {
        return userRecordDao.findAll();
    }

    @Override
    public UserRecord getUser(Integer id) {
        return userRecordDao.getUser(id);
    }


}