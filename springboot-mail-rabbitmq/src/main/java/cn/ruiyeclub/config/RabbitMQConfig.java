package cn.ruiyeclub.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Configurable;

import static cn.ruiyeclub.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static cn.ruiyeclub.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * @Author: Cr.
 * @Date: 2022/6/10
 */
@Configurable
public class RabbitMQConfig {

    // 邮箱队列
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    // 邮箱交换机
    public FanoutExchange emailExchange() {
        return new FanoutExchange(EMAIL_EXCHANGE, true, false);
    }

    // 队列绑定交换机
    public Binding bindingEmailDirect() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange());
    }
}
