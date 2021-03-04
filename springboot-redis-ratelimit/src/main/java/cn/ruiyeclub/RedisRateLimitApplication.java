package cn.ruiyeclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Ray。
 * @Date: 2021/3/4
 */
@SpringBootApplication
public class RedisRateLimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisRateLimitApplication.class,args);
    }
}
