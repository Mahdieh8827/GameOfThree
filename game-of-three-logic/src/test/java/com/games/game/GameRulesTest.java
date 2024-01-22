package com.games.game;


import com.games.rule.game.GameRulesImpl;
import com.games.rule.player.PlayerRulesImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameRulesTest {
    private GameRulesImpl gameRules;

    @Mock
    private PlayerRulesImpl playerRules;

    @Test
    public void should_isOver_returnTrue_whenGameOverAtNumberIsAchieved() {
        // Given
        gameRules = new GameRulesImpl(playerRules,1);

        // When
        boolean isGameOver = gameRules.isOver(1);

        // Then
        Assert.assertTrue(isGameOver);
    }

    @Test
    public void should_isOver_returnFalse_whenGameOverAtNumberIsNotYetAchieved() {
        // Given
        gameRules = new GameRulesImpl(playerRules,1);

        // When
        boolean isGameOver = gameRules.isOver(2);

        // Then
        Assert.assertFalse(isGameOver);
    }
    @Test
    public void should_isReady_returnTrue_whenRequiredPlayerNumberIsAchieved() {
        gameRules = new GameRulesImpl(playerRules,1);
        // Given
        Mockito.when(playerRules.isRequiredPlayerNumberAchieved()).thenReturn(true);

        // When
        boolean isGameReady = gameRules.isReady();

        // Then
        Assert.assertTrue(isGameReady);
    }
    @Test
    public void should_isReady_returnFalse_whenRequiredPlayerNumberIsNotYetAchieved() {
        gameRules = new GameRulesImpl(playerRules,1);
        // Given
        Mockito.when(playerRules.isRequiredPlayerNumberAchieved()).thenReturn(false);

        // When
        boolean isGameReady = gameRules.isReady();

        // Then
        Assert.assertFalse(isGameReady);
    }

}
