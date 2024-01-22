package com.games.rule.game;

import com.games.rule.player.PlayerRules;
public class GameRulesImpl implements GameRules {

    private final int gameOverAtNumber;

    private final PlayerRules playerRules;

    public GameRulesImpl( PlayerRules playerRules, int gameOverAtNumber) {
        this.playerRules = playerRules;
        this.gameOverAtNumber = gameOverAtNumber;
    }

    public boolean isOver(int currentNumber) {
        return currentNumber == gameOverAtNumber;
    }

    public boolean isReady() {
        return playerRules.isRequiredPlayerNumberAchieved();
    }
}
