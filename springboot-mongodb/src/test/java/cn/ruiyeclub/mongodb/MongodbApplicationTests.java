package cn.ruiyeclub.mongodb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MongodbApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        Demo demo = new Demo();
        demo.setId(4);
        demo.setName("凌康");
        demo.setSex("男");
        mongoTemplate.insert(demo);
        demo.setId(5);
        demo.setName("李白");
        demo.setSex("男");
        mongoTemplate.insert(demo);
        log.info("插入成功！");
    }

    @Test
    public void find(){
        List<Demo> demos=mongoTemplate.findAll(Demo.class);
        System.out.println(demos.toString());

        //可以直接根据id查询
        Demo byId = mongoTemplate.findById(2, Demo.class);
        System.out.println(byId);


        /**
         * 根据条件查询 可以查询多个数据
         */
        Query query=new Query(Criteria.where("name").is("李白"));
        List<Demo> demos1 = mongoTemplate.find(query, Demo.class);
        System.out.println(demos1.toString());

    }

    @Test
    public void update(){
//        mongoTemplate.updateFirst(query(where("name").is("我是凌康")), Update.update("sex","123"),Demo.class);
        mongoTemplate.updateFirst(query(where("id").is(2)),Update.update("name","是李白"),Demo.class);
    }

    /**
     * 删除只能根据主键删除
     */
    @Test
    public void delete(){
        Demo demo=new Demo();
//        demo.setName("李白");
        demo.setId(1);
        mongoTemplate.remove(demo);
    }


    class Demo {
        private int id;
        private String name;
        private String sex;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        /**
         * 用于打印对象内容
         */
        @Override
        public String toString() {
            return "Demo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

}
