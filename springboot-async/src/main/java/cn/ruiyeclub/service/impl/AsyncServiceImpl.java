package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.AsyncService;
import cn.ruiyeclub.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Cr.
 * @date: 2022/12/12
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private UserRecordService userRecordService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Async("customExecutor")
    public void sleep() throws InterruptedException {
        UserRecord userRecord = new UserRecord();
        userRecord.setName("小米");
        userRecordService.save(userRecord);
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000 * 5);
            System.out.println(i);
        }
        int a = 1 / 0;
    }
}
