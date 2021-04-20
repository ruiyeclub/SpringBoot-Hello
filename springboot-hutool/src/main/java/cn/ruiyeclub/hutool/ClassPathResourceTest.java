package cn.ruiyeclub.hutool;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * ClassPath单一资源访问类，可以获取classPath下的文件，在Tomcat等容器下，classPath一般是WEB-INF/classes。
 * @Author: Ray。
 * @Date: 2021/1/26
 */
public class ClassPathResourceTest {

    public static void main(String[] args) throws IOException {
        //获取定义在src/main/resources文件夹中的配置文件
        ClassPathResource resource = new ClassPathResource("application.yml");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println("classPath:"+properties);

    }
}
