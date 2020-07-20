package cn.ruiyeclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ruiyeclub.dao.UserRecordDao;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.stereotype.Service;

/**
 * (UserRecord)表服务实现类
 * @author Ray。
 * @data 2020-07-17 21:51:10
 */
@Service("userRecordService")
public class UserRecordServiceImpl extends ServiceImpl<UserRecordDao, UserRecord> implements UserRecordService {

}