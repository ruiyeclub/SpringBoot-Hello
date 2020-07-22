package cn.ruiyeclub.controller;

import cn.ruiyeclub.entity.UserModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ray。
 * @version 1.0
 * @date 2020/5/29 19:22
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Object test(@Valid UserModel userModel){
        return userModel;
    }

    @PostMapping("/testPost")
    public Object testPost(@RequestBody @Valid UserModel userModel){
        return userModel;
    }
}
