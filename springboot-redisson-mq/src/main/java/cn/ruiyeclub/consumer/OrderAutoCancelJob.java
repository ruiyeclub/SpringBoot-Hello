package cn.ruiyeclub.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author LeGreen
 * @title 订单超时未支付自动取消
 * @date 2022/5/5
 */
@Slf4j
@Component
public class OrderAutoCancelJob implements RedisDelayQueueHandle<String> {


    @Override
    public void execute(String data) {
        log.info("订单超时未支付自动取消：{}", data);
        // 执行业务操作
        try {
            // 业务代码
            log.info("订单超时未支付自动取消---业务代码");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
