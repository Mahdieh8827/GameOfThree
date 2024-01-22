package com.games.logic.game;

import com.games.domain.*;
import com.games.logic.player.PlayerHandler;
import com.games.rule.game.GameRules;
import com.games.rule.player.PlayerRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static com.games.domain.GameStatusEnum.*;

public class GameHandlerImpl implements GameHandler {

	private final PlayerHandler playerHandler;
	private static final Logger logger = LoggerFactory.getLogger(GameHandlerImpl.class);

	private final GameRules gameRules;

	private final PlayerRules playerRules;

	private final int minNumber;

	private final int maxNumber;

	public GameHandlerImpl(PlayerHandler playerHandler, GameRules gameRules, PlayerRules playerRules, int minNumber, int maxNumber) {
		this.playerHandler = playerHandler;
		this.gameRules = gameRules;
		this.playerRules = playerRules;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	public GameStatus makeMove(GameMove move) {
		int newCurrentNumber = countCurrentNumber(move);
		GameStatusEnum status = getGameStatus(newCurrentNumber);
		logger.info("Game status is {} in make move", status);
		if (status == OVER) {
			playerHandler.removePlayers();
		}
		return new GameStatus(newCurrentNumber, move.getPlayer(), move.getInputNumber(), status,
				move.getGameMode());
	}

	public GameStatus addNewPlayer(String playerName, GameModeEnum gameMode) {
		playerHandler.add(new Player(playerName));
		if (playerRules.isRequiredPlayerNumberAchieved()) {
			return new GameStatus(getARandomNumber(), playerName, IN_PROGRESS, gameMode);
		}
		return new GameStatus(playerName, WAITING_FOR_PLAYER, gameMode);
	}

	private GameStatusEnum getGameStatus(int currentNumber) {
		logger.info("Game status is {} in getGameStatus, number is {}", gameRules.isOver(currentNumber),currentNumber);
		if (gameRules.isOver(currentNumber)) {
			return OVER;
		}
		return IN_PROGRESS;
	}

	private int countCurrentNumber(GameMove gameMove) {
		return (gameMove.getCurrentNumber() + gameMove.getInputNumber()) / 3;
	}

	private int getARandomNumber() {
		return new Random().ints(minNumber, maxNumber).findFirst().getAsInt();
	}

}
