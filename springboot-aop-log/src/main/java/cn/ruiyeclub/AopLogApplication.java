package cn.ruiyeclub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ruiyeclub.dao")
public class AopLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopLogApplication.class, args);
    }

}
