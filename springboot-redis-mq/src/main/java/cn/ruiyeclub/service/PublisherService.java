package cn.ruiyeclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PublisherService {
 
    @Autowired
    private StringRedisTemplate redisTemplate;
 
    public String sendMessage(String name) {
        try {
            System.out.println("name"+name);
            redisTemplate.convertAndSend("TOPIC_USERNAME", name);
            return "消息发送成功了";
 
        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败了";
        }
    }
}