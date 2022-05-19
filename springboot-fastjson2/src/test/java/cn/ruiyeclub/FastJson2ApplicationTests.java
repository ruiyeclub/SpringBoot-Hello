package cn.ruiyeclub;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Ray。
 * @Date: 2022/5/19
 */
@SpringBootTest
public class FastJson2ApplicationTests {

    /**
     * JSON字符串转换成对象
     */
    @Test
    public void testParseObject() {
        String str = "{\"id\":123}";
        JSONObject jsonObject = JSON.parseObject(str);
        Integer id = jsonObject.getInteger("id");
        System.out.println(id);
    }

    /**
     * JSON字符串转换成数组
     */
    @Test
    public void testParseArray() {
        String str = "[\"id\", 123]";
        JSONArray objects = JSON.parseArray(str);
        String idStr = objects.getString(0);
        System.out.println(idStr);
    }

    /**
     * 将对象转换成JSON字符串
     */
    @Test
    public void testToJSONString() {
        // 定义一个对象
        class Product {
            public int id;
            public String name;
        }

        Product product = new Product();
        product.id = 1001;
        product.name = "DataWorks";

        String s = JSON.toJSONString(product);
        System.out.println(s);

        // 对象到数组
        String s1 = JSON.toJSONString(product, JSONWriter.Feature.BeanToArray);
        System.out.println(s1);
    }
}
