package cn.ruiyeclub.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: shf description:Redission配置类
 * date: 2021/8/3 13:40
 */
@Slf4j
@Configuration
public class RedissionConfig {
    private final String REDISSON_PREFIX = "redis://";
    private final RedisProperties redisProperties;

    public RedissionConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String url = REDISSON_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort();
        // 这里以单台redis服务器为例
        config.useSingleServer()
                .setAddress(url)
                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase())
                .setPingConnectionInterval(2000);
        config.setLockWatchdogTimeout(10000L);

        // 实际开发过程中应该为cluster或者哨兵模式，这里以cluster为例
        //String[] urls = {"127.0.0.1:6379", "127.0.0.2:6379"};
        //config.useClusterServers()
        //        .addNodeAddress(urls);
        try {
            return Redisson.create(config);
        } catch (Exception e) {
            log.error("RedissonClient init redis url:[{}], Exception:", url, e);
            return null;
        }
    }
}