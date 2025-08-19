package cn.ruiyeclub.controller;

import cn.ruiyeclub.constant.RedisDelayQueueEnum;
import cn.ruiyeclub.util.RedisDelayQueueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 延迟队列测试
 * Created by LPB on 2020/04/20.
 */
@RestController
public class RedisDelayQueueController {

    @Autowired
    private RedisDelayQueueUtil redisDelayQueueUtil;

    @PostMapping("/addQueue")
    public void addQueue() {
        // 添加订单支付超时，自动取消订单延迟队列。为了测试效果，延迟10秒钟
        redisDelayQueueUtil.addDelayQueue("订单支付超时，自动取消订单", 10, TimeUnit.SECONDS, RedisDelayQueueEnum.ORDER_AUTO_CANCEL_JOB.getCode());
        // 订单超时未评价，系统默认好评。为了测试效果，延迟20秒钟
        redisDelayQueueUtil.addDelayQueue("自动好评", 30, TimeUnit.SECONDS, RedisDelayQueueEnum.ORDER_AUTO_COMMENT_JOB.getCode());
    }

}