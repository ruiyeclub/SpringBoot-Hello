package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.dao.UserRecordDao;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.AsyncService;
import cn.ruiyeclub.service.UserRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (UserRecord)表服务实现类
 *
 * @author Ray。
 * @date 2020-07-22 14:06:53
 */
@Service("userRecordService")
public class UserRecordServiceImpl extends ServiceImpl<UserRecordDao, UserRecord> implements UserRecordService {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private UserRecordService userRecordService;

    /**
     * 异步方法失败 调用异步方法的方法无法回滚事务
     *
     * @return
     * @throws InterruptedException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int execute() throws InterruptedException {
        UserRecord userRecord = new UserRecord();
        userRecord.setName("xiaoming");
        userRecordService.save(userRecord);
        // 调用异步方法
        asyncService.sleep();
        return 1;
    }

}
