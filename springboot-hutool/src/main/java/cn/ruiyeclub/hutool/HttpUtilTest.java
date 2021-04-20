package cn.ruiyeclub.hutool;

import cn.hutool.http.HttpUtil;

/**
 * Http请求工具类，可以发起GET/POST等请求。
 * @Author: Ray。
 * @Date: 2021/4/20
 */
public class HttpUtilTest {
    public static void main(String[] args) {
        String response = HttpUtil.get("http://localhost:8080/hutool/covert");
        System.out.println("HttpUtil get:"+response);
    }
}
