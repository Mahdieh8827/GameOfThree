package com.games.utility;

import com.games.controller.ComputerStompHandler;
import com.games.dto.GameStartDto;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import static com.games.domain.GameModeEnum.COMPUTER;

public class GotControllerUtility {
    public static boolean shouldAddComputerPlayer(GameStartDto gameStartDto, String computerPlayerName) {
        return COMPUTER == gameStartDto.getGameMode()
                && !computerPlayerName.equals(gameStartDto.getPlayerName());
    }

    public static void addComputer(String computerPlayerName) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.connectAsync( "ws://"+WebSocketProperties.HOST +":"+WebSocketProperties.PORT +"/"+WebSocketProperties.WEBSOCKET, new ComputerStompHandler(computerPlayerName));
    }
}
