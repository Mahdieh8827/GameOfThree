package com.games.domain;

public class GameStatus {
    private Integer currentNumber;

    private String lastPlayer;

    private Integer lastNumberAdded;

    private GameStatusEnum status;

    private GameModeEnum gameMode;

    public GameStatus(String lastPlayer, GameStatusEnum status, GameModeEnum gameMode) {
        this.lastPlayer = lastPlayer;
        this.status = status;
        this.gameMode = gameMode;
    }

    public GameStatus(Integer currentNumber, String lastPlayer, GameStatusEnum status, GameModeEnum gameMode) {
        this.currentNumber = currentNumber;
        this.lastPlayer = lastPlayer;
        this.status = status;
        this.gameMode = gameMode;
    }

    public GameStatus(Integer currentNumber, String lastPlayer, Integer lastNumberAdded, GameStatusEnum status,
                     GameModeEnum gameMode) {
        this.currentNumber = currentNumber;
        this.lastPlayer = lastPlayer;
        this.lastNumberAdded = lastNumberAdded;
        this.status = status;
        this.gameMode = gameMode;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(String lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public Integer getLastNumberAdded() {
        return lastNumberAdded;
    }

    public void setLastNumberAdded(Integer lastNumberAdded) {
        this.lastNumberAdded = lastNumberAdded;
    }

    public GameStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GameStatusEnum status) {
        this.status = status;
    }

    public GameModeEnum getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameModeEnum gameMode) {
        this.gameMode = gameMode;
    }
}
