package com.games.domain;

public class GameMove {

	private final Integer currentNumber;

	private final String player;

	private final Integer inputNumber;

	private final GameModeEnum gameMode;

	public GameMove(Integer currentNumber, String player, Integer inputNumber, GameModeEnum gameMode) {
		this.currentNumber = currentNumber;
		this.player = player;
		this.inputNumber = inputNumber;
		this.gameMode = gameMode;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public String getPlayer() {
		return player;
	}

	public Integer getInputNumber() {
		return inputNumber;
	}

	public GameModeEnum getGameMode() {
		return gameMode;
	}
}
