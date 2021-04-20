package cn.ruiyeclub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ray。
 * @date 2020/7/18 10:07
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.ruiyeclub.dao"})
public class JApiDocsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JApiDocsApplication.class, args);
    }
}
