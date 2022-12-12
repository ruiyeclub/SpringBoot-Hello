package cn.ruiyeclub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Cr.
 * @date: 2022/12/12
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.ruiyeclub.dao"})
public class AsyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }
}
