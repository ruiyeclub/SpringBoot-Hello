package cn.ruiyeclub.consumer;

import cn.ruiyeclub.dto.EmailDTO;
import cn.ruiyeclub.utils.MailUtils;
import com.alibaba.fastjson2.JSON;
import freemarker.template.TemplateException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

import static cn.ruiyeclub.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * @author: Cr.
 * @date: 2022/6/10
 */
@Component
@RabbitListener(queues = EMAIL_QUEUE)
public class EmailConsumer {

    @Autowired
    private MailUtils mailUtils;

    @RabbitHandler
    public void process(byte[] data) throws TemplateException, IOException, MessagingException {
        EmailDTO emailDTO = JSON.parseObject(new String(data), EmailDTO.class);
        mailUtils.sendTemplateMail(emailDTO.getEmail(), emailDTO.getSubject(), emailDTO.getContent(), "code.html");
    }
}
