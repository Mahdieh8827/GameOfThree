package com.games.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebSocketProperties {

    public static String PORT;
    public static String HOST;
    public static String WEBSOCKET;

    @Value("${server.port}")
    public void setPort(String port) {
        WebSocketProperties.PORT = port;
    }

    @Value("${server.host}")
    public void setHost(String host) {
        WebSocketProperties.HOST = host;
    }

    @Value("${gameofthree.websocket-name}")
    public void setWebSocketName(String webSocketName) {
        WebSocketProperties.WEBSOCKET = webSocketName;
    }
}
