package cn.ruiyeclub.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @NacosValue(value = "${helloworld:HelloWorld}", autoRefreshed = true)
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        return hello;
    }

}
