package cn.ruiyeclub.service;

public interface SendSmsService {

    /**
     * 发送短信验证码的接口
     * @param phoneNum 手机号
     * @param code     验证码
     * @return
     */
    boolean sendSms(String phoneNum, String code);

}