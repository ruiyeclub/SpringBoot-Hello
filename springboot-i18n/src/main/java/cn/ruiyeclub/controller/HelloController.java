package cn.ruiyeclub.controller;

import cn.ruiyeclub.utils.MessageUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ray。
 * @date 2020/8/4 18:01
 */
@RestController
@RequestMapping("/i18n")
public class HelloController {

    @RequestMapping("/user")
    public String getUserName() {
        return MessageUtils.get("user.username");
    }
}
