package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.dao.UserRecordDao;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * (UserRecord)表服务实现类
 *
 * @author Ray。
 * @date 2020-07-18 10:15:37
 */
@Service("userRecordService")
public class UserRecordServiceImpl extends ServiceImpl<UserRecordDao, UserRecord> implements UserRecordService {

}