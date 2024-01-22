package com.games.controller;

import static com.games.domain.GameModeEnum.COMPUTER;
import com.games.domain.GameStatus;
import com.games.domain.GameStatusEnum;
import com.games.dto.GameMoveDto;
import com.games.dto.GameStartDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;
import java.util.stream.Stream;

public class ComputerStompHandler implements StompSessionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerStompHandler.class);

    private final String computerPlayerName;

    public ComputerStompHandler(String computerPlayerName) {
        this.computerPlayerName = computerPlayerName;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/gameOfThree", new StompFrameHandler() {

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                    GameStatus gameStatus = (GameStatus) payload;
                    if (GameStatusEnum.OVER == gameStatus.getStatus()) {
                        session.disconnect();
                    } else if (!computerPlayerName.equals(gameStatus.getLastPlayer())) {
                        session.send("/app/move", new GameMoveDto(gameStatus.getCurrentNumber(), computerPlayerName,
                                countNumberToAdd(gameStatus.getCurrentNumber()), COMPUTER));
                    }
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return GameStatus.class;
            }
        });
        session.send("/app/start", new GameStartDto(computerPlayerName, COMPUTER));
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return GameStatus.class;
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        LOGGER.error(exception.getMessage(), exception);
        throw new RuntimeException(exception.getMessage());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
                                Throwable exception) {
        LOGGER.error(exception.getMessage(), exception);
        throw new RuntimeException(exception.getMessage());
    }

    private Integer countNumberToAdd(Integer currentNumber) {
        return Stream.of(-1, 0, 1).filter(number -> (currentNumber + number) % 3 == 0).findFirst().get();
    }
}

