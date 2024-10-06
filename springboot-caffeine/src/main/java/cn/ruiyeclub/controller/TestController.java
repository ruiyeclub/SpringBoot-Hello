package cn.ruiyeclub.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Cr.
 * @date 2024/10/6
 */
@RestController
public class TestController {

    /**
     * key名为p_name，通过key缓存value
     *
     * @param name
     * @return
     */
    @Cacheable(value = "say", cacheManager = "caffeineCacheManager", key = "'p_' + #name")
    @GetMapping(path = "say")
    public String sayHello(String name) {
        return "hello " + name + "-->" + UUID.randomUUID();
    }

    /**
     * 当age为偶数时才写缓存，否则不写。
     *
     * @param age
     * @return
     */
    @Cacheable(value = "condition", cacheManager = "caffeineCacheManager", key = "#age", condition = "#age % 2 == 0")
    @GetMapping(path = "condition")
    public String setByCondition(Integer age) {
        return "condition: " + age + "-->" + UUID.randomUUID();
    }

    /**
     * 和condition方法相反
     *
     * @param age
     * @return
     */
    @Cacheable(value = "unless", cacheManager = "caffeineCacheManager", key = "#age", unless = "#age % 2 == 0")
    @GetMapping(path = "unless")
    public String setByUnless(Integer age) {
        return "unless: " + age + "-->" + UUID.randomUUID();
    }

    /**
     * 可修改（重新赋值）key为say的值
     *
     * @param name
     * @return
     */
    @CachePut(value = "say", cacheManager = "caffeineCacheManager", key = "'p_' + #name")
    @GetMapping(path = "cachePut")
    public String cachePut(String name) {
        return "hello " + name + "-->" + UUID.randomUUID();
    }


    /**
     * 可删除key为say的值
     *
     * @param name
     * @return
     */
    @CacheEvict(value = "say", cacheManager = "caffeineCacheManager", key = "'p_' + #name")
    @GetMapping(path = "evict")
    public String evict(String name) {
        return "hello " + name + "-->" + UUID.randomUUID();
    }


    /**
     * 可以同时应用多个缓存注解，实现复杂的缓存逻辑
     *
     * @param name
     * @param age
     * @return
     */
    @Caching(cacheable = @Cacheable(value = "say", cacheManager = "caffeineCacheManager", key = "'p_' + #name")
            , evict = @CacheEvict(value = "condition", cacheManager = "caffeineCacheManager", key = "#age"))
    @GetMapping(path = "caching")
    public String caching(String name, Integer age) {
        return "caching " + name + "-->" + UUID.randomUUID();
    }


}
