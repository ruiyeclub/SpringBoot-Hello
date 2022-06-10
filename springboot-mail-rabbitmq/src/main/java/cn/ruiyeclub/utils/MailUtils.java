package cn.ruiyeclub.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ray。
 * @date 2020/7/28 19:10
 */
@Component
@Slf4j
public class MailUtils {

    /**
     * Spring官方提供的集成邮件服务的实现类，目前是Java后端发送邮件和集成邮件服务的主流工具。
     */
    @Resource
    private JavaMailSender mailSender;

    /**
     * 从配置文件中注入发件人的姓名
     */
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送html邮件
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        //注意这里使用的是MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        //第二个参数：格式是否为html
        helper.setText(content, true);
        mailSender.send(message);
    }

    public void sendTemplateMail(String to, String subject, String code, String template) throws IOException, TemplateException, MessagingException {
        // 获得模板
        Template template1 = freeMarkerConfigurer.getConfiguration().getTemplate(template);
        // 使用Map作为数据模型，定义属性和值
        Map<String, Object> model = new HashMap<>();
        model.put("code", code);
        // 传入数据模型到模板，替代模板中的占位符，并将模板转化为html字符串
        String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template1, model);
        // 该方法本质上还是发送html邮件，调用之前发送html邮件的方法
        this.sendHtmlMail(to, subject, templateHtml);
    }

    /**
     * 发送带附件的邮件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        //要带附件第二个参数设为true
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }
}
