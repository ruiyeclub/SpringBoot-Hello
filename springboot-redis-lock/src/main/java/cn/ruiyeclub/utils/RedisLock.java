package cn.ruiyeclub.utils;

import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisLock {

    /**
     * 锁默认前缀
     */
    private static final String DEFAULT_LOCK_PREFIX = "LOCK:";
    /**
     * 锁默认过期时间，默认 5 分钟
     */
    private static final long DEFAULT_EXPIRE = 5L;
    /**
     * 锁默认过期时间单位
     */
    private static final TimeUnit DEFAULT_UNIT = TimeUnit.SECONDS;
    private RedisTemplate redisTemplate;
    private String key;
    private String value;
    private long expire;
    private TimeUnit unit;
    @Getter
    private String redisKey;

    private RedisLock() {}

    public static RedisLock newLock(RedisTemplate redisTemplate, String key) {
        return newLock(redisTemplate, key, DEFAULT_EXPIRE, DEFAULT_UNIT);
    }

    public static RedisLock newLock(RedisTemplate redisTemplate, String key, long expire, TimeUnit unit) {
        RedisLock lock = new RedisLock();
        lock.redisTemplate = redisTemplate;
        lock.key = key;
        lock.redisKey = DEFAULT_LOCK_PREFIX + key;
        lock.expire = expire;
        lock.unit = unit;
        lock.value = UUID.randomUUID().toString();
        return lock;
    }

    public boolean tryLock() {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return 'OK' " +
                "else return redis.call('set', KEYS[1], ARGV[1],'EX',ARGV[2],'NX') end";
        String result = (String) redisTemplate.execute(RedisScript.of(script, String.class),
                Collections.singletonList(getRedisKey()), this.value, String.valueOf(this.unit.toSeconds(this.expire)));
        return "OK".equals(result);
    }

    public boolean release() {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then redis.call('del', KEYS[1]) return 1 else return 0 end";
        Long result = (Long) redisTemplate.execute(RedisScript.of(script, Long.class),
                Collections.singletonList(getRedisKey()), this.value);
        return result != null && result > 0;
    }

}
