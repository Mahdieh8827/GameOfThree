package com.games.dto;


import com.games.domain.GameModeEnum;
import com.games.domain.GameMove;

public class GameMoveDto {
	private Integer currentNumber;

	private String player;

	private Integer inputNumber;

	private GameModeEnum gameMode;

	public GameMoveDto(Integer currentNumber, String player, Integer inputNumber, GameModeEnum gameMode) {
		this.currentNumber = currentNumber;
		this.player = player;
		this.inputNumber = inputNumber;
		this.gameMode = gameMode;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Integer getInputNumber() {
		return inputNumber;
	}

	public void setInputNumber(Integer inputNumber) {
		this.inputNumber = inputNumber;
	}

	public GameModeEnum getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameModeEnum gameMode) {
		this.gameMode = gameMode;
	}

	public GameMove getGameMove() {
		return new GameMove(currentNumber, player, inputNumber, gameMode);
	}

}
