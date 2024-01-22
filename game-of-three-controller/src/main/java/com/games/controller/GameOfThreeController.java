package com.games.controller;

import static com.games.domain.GameModeEnum.COMPUTER;
import static com.games.utility.GotControllerUtility.addComputer;
import static com.games.utility.GotControllerUtility.shouldAddComputerPlayer;

import com.games.domain.GameStatus;
import com.games.dto.GameMoveDto;
import com.games.dto.GameStartDto;
import com.games.logic.game.GameHandler;
import com.games.logic.player.PlayerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameOfThreeController {

    private final GameHandler gameHandler;
    private static final Logger logger = LoggerFactory.getLogger(GameOfThreeController.class);

    private final PlayerHandler playerHandler;

    @Value("${gameofthree.computer.player-name}")
    private String computerPlayerName;

    public GameOfThreeController(GameHandler gameHandler, PlayerHandler playerHandler) {
        this.gameHandler = gameHandler;
        this.playerHandler = playerHandler;
    }

    @MessageMapping("/start")
    @SendTo("/topic/gameOfThree")
    public GameStatus startGame(GameStartDto gameStartDto) {
            GameStatus gameStatus = gameHandler.addNewPlayer(gameStartDto.getPlayerName(),
                    gameStartDto.getGameMode());
            if (shouldAddComputerPlayer(gameStartDto, computerPlayerName)) {
                addComputer(computerPlayerName);
            }

            logger.info("Game status is {} in start game", gameStatus.getStatus());
            return gameStatus;
    }

    @MessageMapping("/move")
    @SendTo("/topic/gameOfThree")
    public GameStatus makeMove(GameMoveDto gameMoveDto) {
        return gameHandler.makeMove(gameMoveDto.getGameMove());
    }

    @GetMapping("/isAPlayerWaiting")
    public boolean isAPlayerWaiting() {
        return playerHandler.isAPlayerWaiting();
    }

}
