package cn.ruiyeclub.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author LeGreen
 * @title 自动好评
 * @date 2022/5/5
 */
@Slf4j
@Component
public class OrderAutoCommentJob implements RedisDelayQueueHandle<String> {


    @Override
    public void execute(String data) {
        log.info("自动好评：{}", data);
        //执行业务操作
        try {
            log.info("自动好评---业务代码");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
