package cn.ruiyeclub;

import cn.ruiyeclub.util.LocalHostUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Ray。
 * @date 2020/7/18 10:07
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"cn.ruiyeclub.dao"})
public class Knife4jApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(Knife4jApplication.class, args);
        Environment env = application.getEnvironment();
        var ip = LocalHostUtils.getLocalHost();
        var port = env.getProperty("server.port");
        var banner = "\n----------------------------------------------------------\n\t" +
                "启动成功！\n\t" +
                "Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + "/\n\t" +
                "Swagger-UI: http://" + ip + ":" + port + "/doc.html\n" +
                "-------------------------------------------------------------------------";
        log.info(banner);
    }
}
