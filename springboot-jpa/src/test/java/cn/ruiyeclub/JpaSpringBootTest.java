package cn.ruiyeclub;

import cn.ruiyeclub.dao.UserRecordDao;
import cn.ruiyeclub.entity.UserRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ray。
 * @date 2020/7/29 19:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class JpaSpringBootTest {

    @Resource
    private UserRecordDao userRecordDao;

    /**
     * 新增用户
     */
    @Test
    public void testAddUserRecord(){
        UserRecord user = new UserRecord();
        user.setName("zhangsan");
        user.setAge(12);
        userRecordDao.save(user);

        UserRecord user2 = new UserRecord();
        user2.setName("lishi");
        user2.setAge(22);
        userRecordDao.save(user2);
    }

    /**
     * 删除用户(根据对象删除时，必须要有ID属性)
     */
    @Test
    public void testDelUserRecord(){
        userRecordDao.deleteById(4);
    }

    /**
     * 修改用户信息
     */
    @Test
    public void testUpdUserRecord(){
        UserRecord user = new UserRecord();
        user.setId(2);
        user.setName("zhangsan11");
        user.setAge(122);
        userRecordDao.save(user);
    }

    /**
     * 查询用户
     */
    @Test
    public void testQueryUserRecord(){
        UserRecord user = userRecordDao.findByAge(22);
        System.out.println(user.getName());

        UserRecord user2 = userRecordDao.findByNameAndAge("lishi", 22);
        System.out.println(user2.getName());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void testQueryUserRecordList(){
        List<UserRecord> list = userRecordDao.findAll();
        for (UserRecord user : list) {
            System.out.println(user.getName());
        }
    }
}
