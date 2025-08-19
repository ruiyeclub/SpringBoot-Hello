package cn.ruiyeclub.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RedisDelayQueueEnum {

    ORDER_AUTO_CANCEL_JOB("ORDER_AUTO_CANCEL_JOB", "订单超时未支付自动取消", "orderAutoCancelJob"),
    ORDER_AUTO_COMMENT_JOB("ORDER_AUTO_COMMENT_JOB", "自动好评", "orderAutoCommentJob");

    /**
     * 延迟队列 Redis Key
     */
    private String code;

    /**
     * 描述
     */
    private String name;

    /**
     * 延迟队列具体业务实现的 Bean
     * 可通过 Spring 的上下文获取
     */
    private String beanId;
}
