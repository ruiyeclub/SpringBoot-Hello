package cn.ruiyeclub.service.impl;

import cn.ruiyeclub.service.SendSmsService;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ray。
 * @Date: 2020/12/2
 */
@Service
@Slf4j
public class SendSmsServiceImpl implements SendSmsService {

    @Value("${aliyun.accessKeyID}")
    private String accessKeyID;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Override
    public boolean sendSms(String phoneNum, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shenzhen", accessKeyID, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2020-12-01");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shenzhen");
        request.putQueryParameter("SignName", "签名名称");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("TemplateCode", "模板code");

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("返回的消息:{}",JSON.parseObject(response.getData(), Map.class).get("Message").toString());
            return response.getHttpResponse().isSuccess();

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}

