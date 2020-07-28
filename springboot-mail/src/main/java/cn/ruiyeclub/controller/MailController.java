package cn.ruiyeclub.controller;

import cn.ruiyeclub.utils.MailUtils;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@Api(tags = "邮件管理")
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailUtils mailUtils;

    /**
     * 发送注册验证码
     * @return 验证码
     * @throws Exception
     */
    @ApiOperation("发送注册验证码")
    @GetMapping("/test")
    public String send(){
        mailUtils.sendSimpleMail("ruiyeclub@foxmail.com","普通文本邮件","普通文本邮件内容");
        return "OK";
    }

    /**
     * 发送注册验证码
     * @return 验证码
     * @throws Exception
     */
    @ApiOperation("发送注册验证码")
    @PostMapping("/sendHtml")
    public String sendTemplateMail() throws MessagingException {
        mailUtils.sendHtmlMail("ruiyeclub@foxmail.com","一封html测试邮件",
                "<div style=\"text-align: center;position: absolute;\" >\n"
                        +"<h3>\"一封html测试邮件\"</h3>\n"
                        + "<div>一封html测试邮件</div>\n"
                        + "</div>");
        return "OK";
    }

    @ApiOperation("发送html模板邮件")
    @PostMapping("/sendTemplate")
    public String sendTemplate() throws MessagingException, IOException, TemplateException {
        mailUtils.sendTemplateMail("ruiyeclub@foxmail.com", "基于模板的html邮件", "hello.html");
        return "OK";
    }

    @ApiOperation("发送带附件的邮件")
    @GetMapping("sendAttachmentsMail")
    public String sendAttachmentsMail() throws MessagingException {
        String filePath = "D:\\projects\\springboot\\template.png";
        mailUtils.sendAttachmentsMail("xxxx@xx.com", "带附件的邮件", "邮件中有附件", filePath);
        return "OK";
    }


}