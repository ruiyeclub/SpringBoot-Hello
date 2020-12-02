package cn.ruiyeclub.controller;

import cn.ruiyeclub.service.SendSmsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class SendSmsController {

    @Resource
    private SendSmsService sendSmsService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @PostMapping("/sendSms")
    public String sendSms(String phoneNum) {
        if(null==phoneNum){
            return "phoneNum为null";
        }
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();

        // 根据手机号进行查询
        String phone = stringR.get(phoneNum);

        // 如果手机号在redis中不存在的话才进行发送验证码操作
        if (StringUtils.isEmpty(phone)) {
            // 生成6位随机数
            String code = String.valueOf(Math.random()).substring(3, 9);
            // 调用业务层接口 发送验证码
            boolean sendSmsFlag = sendSmsService.sendSms(phoneNum, code);
            if (sendSmsFlag) {
                // 发送成功之后往redis中存入该手机号以及验证码 并设置超时时间 5 分钟
                stringR.set(phoneNum, code, 5, TimeUnit.MINUTES);
            }
            return "发送验证码到：" + phoneNum + "成功! " + "Message:" + sendSmsFlag;
        } else {
            return "该手机号：" + phoneNum + " 剩余：" + redisTemplate.getExpire(phoneNum) + "秒后可再次进行发送！";
        }
    }

    @GetMapping("/checkCode/{key}/{code}")
    public String checkCode(@PathVariable("key") String number, @PathVariable("code") String code) {
        // 获取到操作String的对象
        ValueOperations<String, String> stringR = redisTemplate.opsForValue();
        // 根据Key进行查询
        String redisCode = stringR.get(number);
        if (code.equals(redisCode)) {
            return "成功";
        } else {
            return redisCode == null ? "请先获取验证码在进行校验！" : "错误";
        }
    }
}