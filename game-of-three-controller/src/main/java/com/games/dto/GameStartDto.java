package com.games.dto;

import com.games.domain.GameModeEnum;

public class GameStartDto {

    public GameStartDto(String playerName, GameModeEnum gameMode) {
        this.playerName = playerName;
        this.gameMode = gameMode;
    }

    private String playerName;

    private GameModeEnum gameMode;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GameModeEnum getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameModeEnum gameMode) {
        this.gameMode = gameMode;
    }
}
