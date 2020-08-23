package cn.ruiyeclub.controller;

import cn.ruiyeclub.entity.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Ray。
 * @version 1.0
 * @date 2020/5/29 19:22
 */
@Api(tags = "测试")
@RestController
@Validated
public class TestController {

    @ApiOperation("测试表单")
    @GetMapping("/test")
    public Object test(@Validated UserModel userModel){
        return userModel;
    }

    @ApiOperation("测试Json")
    @PostMapping("/testPost")
    public Object testPost(@RequestBody @Validated UserModel userModel){
        return userModel;
    }

    @ApiOperation("测试参数")
    @GetMapping("test1")
    public String test1(
            @NotBlank(message = "名称不为空") String name,
            @NotBlank(message = "邮箱不为空")
            @Email(message = "邮箱格式不对") String email) {
        return "success";
    }
}
