package cn.ruiyeclub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer, WebMvcConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;

    /**
     * WebSocket 是基于同源策略的，这意味着浏览器要求 WebSocket 连接的来源（协议、域名和端口）必须与页面来源相同。
     * 所以需要设置.setAllowedOrigins("*") 并且配置跨域
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 使用chat.html打开下面注释
//        registry.addHandler(webSocketHandler, "/connect").withSockJS(); // .withSockJS()只能用sockjs连接
        // 使用chat_v2.html打开下面
        registry.addHandler(webSocketHandler, "/connect")
                .setAllowedOrigins("*");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/connect/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}