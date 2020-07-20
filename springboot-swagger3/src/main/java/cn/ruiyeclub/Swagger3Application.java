package cn.ruiyeclub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Ray。
 * @date 2020/7/18 10:07
 */
@EnableOpenApi
@SpringBootApplication
@MapperScan(basePackages = {"cn.ruiyeclub.dao"})
public class Swagger3Application {
    public static void main(String[] args) {
        SpringApplication.run(Swagger3Application.class, args);
    }
}
