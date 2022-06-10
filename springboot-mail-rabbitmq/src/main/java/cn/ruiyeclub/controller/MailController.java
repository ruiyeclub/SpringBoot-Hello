package cn.ruiyeclub.controller;

import cn.ruiyeclub.dto.EmailDTO;
import com.alibaba.fastjson2.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.ruiyeclub.constant.MQPrefixConst.EMAIL_EXCHANGE;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/send")
    public String sendTemplate(String mailAddress) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(mailAddress);
        emailDTO.setSubject("验证码");
        emailDTO.setContent("code123");

        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // todo 缓存
        return "发送成功";
    }

}