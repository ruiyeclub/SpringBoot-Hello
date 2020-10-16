package cn.ruiyeclub.service;

import cn.ruiyeclub.entity.UserRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (UserRecord)表服务接口
 *
 * @author Ray。
 * @since 2020-10-16 16:29:28
 */
public interface UserRecordService extends IService<UserRecord> {

    int updateAgeById(UserRecord user);
}