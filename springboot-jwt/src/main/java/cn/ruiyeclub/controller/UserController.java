package cn.ruiyeclub.controller;

import cn.ruiyeclub.annotation.CheckToken;
import cn.ruiyeclub.annotation.LoginToken;
import cn.ruiyeclub.entity.JsonData;
import cn.ruiyeclub.entity.User;
import cn.ruiyeclub.service.UserService;
import cn.ruiyeclub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Ray。
 * @create 2020-05-24 10:53
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    @LoginToken
    public JsonData login(@RequestBody User user) {
        if(user==null || user.getName()==null || user.getPassword()==null){
            return JsonData.buildError("请检查登录参数");
        }
        User userForBase = userService.findByUsername(user);
        if (userForBase == null) {
            return JsonData.buildError("登录失败,用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return JsonData.buildError("登录失败,密码错误");
            } else {
                String token = JwtUtils.geneJsonWebToken(userForBase);
                Map map=new HashMap();
                map.put("token", token);
                map.put("user", userForBase);
                return JsonData.buildSuccess(map);
            }
        }
    }

    //查看个人信息
    @CheckToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }


}
