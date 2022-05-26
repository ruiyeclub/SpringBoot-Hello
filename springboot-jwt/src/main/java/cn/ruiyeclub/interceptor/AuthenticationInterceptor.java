package cn.ruiyeclub.interceptor;

import cn.ruiyeclub.annotation.CheckToken;
import cn.ruiyeclub.annotation.LoginToken;
import cn.ruiyeclub.utils.JsonData;
import cn.ruiyeclub.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Ray。
 * @create 2020-05-24 10:51
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    public static final Gson gson = new Gson();

    /**
     * 进入controller之前进行拦截
     *
     * @param request
     * @param response
     * @param object
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginToken注释，有则跳过认证
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                return true;
            }
        }

        //前面是不需要token验证的 从 http 请求头中取出 token
        String token = request.getHeader("token");
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                if (token != null) {
                    Claims claims = JwtUtils.checkJWT(token);
                    if (null == claims) {
                        sendJsonMessage(response, JsonData.buildError("token有误"));
                        return false;
                    }
                    Integer userId = (Integer) claims.get("id");
                    String name = (String) claims.get("name");

                    request.setAttribute("userId", userId);
                    request.setAttribute("name", name);
                    return true;
                }
                //token为null的话 返回一段json给前端
                sendJsonMessage(response, JsonData.buildError("请登录"));
                return false;
            }
        }
        //没有使用注解的方法 直接返回true
        return true;
    }

    /**
     * 响应数据给前端
     *
     * @param response
     * @param obj
     * @throws IOException
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(gson.toJson(obj));
        writer.close();
        response.flushBuffer();
    }
}