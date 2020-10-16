package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.dao.UserRecordDao;
import cn.ruiyeclub.entity.UserRecord;
import cn.ruiyeclub.service.UserRecordService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.*;

/**
 * (UserRecord)表服务实现类
 *
 * @author Ray。
 * @since 2020-10-16 16:29:29
 */
@Service("userRecordService")
@Slf4j
public class UserRecordServiceImpl extends ServiceImpl<UserRecordDao, UserRecord> implements UserRecordService {

    /**
     * 使用CountDownLatch发令枪来模拟线程同时并发的情况，发令枪设为32，即32个线程同时去请求修改年龄
     */
    private static CountDownLatch latch = new CountDownLatch(32);

    @Resource
    private UserRecordDao userRecordDao;

    @Override
    public int updateAgeById(UserRecord user) {
        //线程池中有10个
        ExecutorService fix = newFixedThreadPool(10);
        Future<Integer> future = null;
        //循环32次，32次之后发令枪会变成0，32个线程开始同时执行
        for (int i=0; i<32; i++){
            Runnable task = ()-> {
              try {
                  //CountDownLatch发令枪等待
                  latch.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              UserRecord selectUser = userRecordDao.selectById(user.getId());
              if(selectUser.getAge()>0){
                  System.out.println("年龄："+selectUser.getAge());
                  //年龄大于0的 执行年龄减1
                  userRecordDao.updateAgeById(user.getId());
                  System.out.println("Task is running by " + Thread.currentThread().getName());
              }
            };
            //循环一次发令枪减1
            latch.countDown();
            System.out.println(latch);
            //提交任务
            future = (Future<Integer>) fix.submit(task);
            log.info("打印future: {}",future);
        }
        fix.shutdown();
        return 0;
    }
}