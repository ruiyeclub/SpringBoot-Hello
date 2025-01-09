package cn.ruiyeclub.controller;

import cn.ruiyeclub.dto.LoginDTO;
import cn.ruiyeclub.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录
 */
@RestController
public class LoginController {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //生成验证码
        captchaService.create(response, uuid);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginDTO login) {
        //验证码是否正确
        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        if (!flag) {
            return "验证码错误";
        }
        return "ok";
    }

}