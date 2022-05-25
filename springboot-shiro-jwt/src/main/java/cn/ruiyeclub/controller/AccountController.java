package cn.ruiyeclub.controller;

import cn.hutool.crypto.SecureUtil;
import cn.ruiyeclub.dto.LoginDto;
import cn.ruiyeclub.entity.SysUser;
import cn.ruiyeclub.result.Result;
import cn.ruiyeclub.service.SysUserService;
import cn.ruiyeclub.util.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    SysUserService sysUserService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto) {

        SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = JwtUtils.generateToken(user);
        return Result.succ(jwt);
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    @GetMapping("/getUserById")
    public Result getUserById(Integer id) {
        return Result.succ(sysUserService.getById(id));
    }

}
