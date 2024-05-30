package cn.ruiyeclub.socket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketClient {

    /**
     * 持久化客户端
     */
    private static ConcurrentHashMap<String, org.java_websocket.client.WebSocketClient> websockets = new ConcurrentHashMap<>();
    /**
     * socket实例化
     */
    private org.java_websocket.client.WebSocketClient webSocketClient;


    public void send(String msg, WebSocketSession session) {
        String id = session.getId();
//        System.out.println(id);
        org.java_websocket.client.WebSocketClient webSocketClient1 = websockets.get(id);
        if (webSocketClient1 == null) {
            connect(session);
        } else {
            log.info("二次连接");
            webSocketClient = webSocketClient1;
            this.sendMessage(msg);
        }
//        sendMessage(msg);
    }


    public void connect(WebSocketSession session) {
        try {
            URI uri = new URI("wss://streaming.forexpros.com/echo/687/dwo1lpgc/websocket");
            webSocketClient = new org.java_websocket.client.WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    log.info("Connected to WebSocket server");
                }

                @Override
                public void onMessage(String message) {
                    log.info("Received message: " + message);
                    // 在这里处理接收到的消息
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("Disconnected from WebSocket server with exit code " + code + " additional info: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    log.error("An error occurred:", ex);
                }
            };
            webSocketClient.connect();
            websockets.put(session.getId(), webSocketClient);
            log.info("当前用户id: {}", session.getId());
            log.info("当前客户端人数: {}", websockets.size());

            // 发送请求给服务端
//            while (true) {
//                Thread.sleep(3000);
//                // 自定义事件`push_data_event` -> 向服务端发送消息
//                sendMessage("[\"{ \\\"_event\\\": \\\"heartbeat\\\", \\\"data\\\": \\\"h\\\"}\"]");
//            }
//            sendMessage(msg);
        } catch (URISyntaxException e) {
            log.error("Error connecting to WebSocket server", e);
        }
    }

    public void disconnect() {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    public void sendMessage(String message) {
        if (webSocketClient != null && webSocketClient.isOpen()) {
            webSocketClient.send(message);
        } else {
            log.warn("WebSocket is not connected.");
        }
    }

}