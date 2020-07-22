package com.ruiyeclub.controller;

import com.ruiyeclub.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @RequestBody将前端的json对象转化为java对象
     * @param student
     */
    @PostMapping("/set")
    public void set(@RequestBody Student student){
        redisTemplate.opsForValue().set("student",student);
    }

    /**
     * 通过key值查询
     * @param key
     * @return
     */
    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key){
        return (Student)redisTemplate.opsForValue().get(key);
    }

    /**
     * 通过key值删除
     * @param key
     * @return
     */
    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key")String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    /**
     * 字符串数据类型
     * @return
     */
    @GetMapping("/string")
    public String stringTest(){
        redisTemplate.opsForValue().set("str","Hello world");
        String str= (String) redisTemplate.opsForValue().get("str");
        return str;

    }

    /**
     * 列表数据类型
     * @return
     */
    @GetMapping("/list")
    public List<String> listTest(){
        ListOperations<String,String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list","hello");
        listOperations.leftPush("list","world");
        listOperations.leftPush("list","Java");
        List<String> list=listOperations.range("list",0,2);
        return list;
    }

    /**
     * 集合数据类型
     * @return
     */
    @GetMapping("/set")
    public Set<String> setTest(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("set","Hello");
        setOperations.add("set","Hello");
        setOperations.add("set","Hello");
        setOperations.add("set","World");
        setOperations.add("set","World");
        Set set = setOperations.members("set");
        return set;
    }

    /**
     * 有序集合数据类型
     * @return
     */
    @GetMapping("/zset")
    public Set<String> zsetTest(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset","Hello",1);
        zSetOperations.add("zset","Hello",3);
        zSetOperations.add("zset","Java",2);
        Set zset=zSetOperations.range("zset",0,2);
        return zset;
    }

    /**
     * 哈希数据类型
     */
    @GetMapping("/hash")
    public void hashTest(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("key","hashkey","hello");
        System.out.println(hashOperations.get("key","hashkey"));

    }
}
