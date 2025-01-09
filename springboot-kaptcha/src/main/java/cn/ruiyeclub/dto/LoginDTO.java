package cn.ruiyeclub.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单
 */
@Data
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String captcha;

    private String uuid;
}