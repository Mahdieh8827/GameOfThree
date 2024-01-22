package com.games.game;


import com.games.domain.GameModeEnum;
import static com.games.domain.GameModeEnum.COMPUTER;
import static com.games.domain.GameModeEnum.HUMAN;
import com.games.domain.GameMove;
import com.games.domain.GameStatus;
import static com.games.domain.GameStatusEnum.IN_PROGRESS;
import static com.games.domain.GameStatusEnum.OVER;
import static com.games.domain.GameStatusEnum.WAITING_FOR_PLAYER;
import com.games.logic.game.GameHandlerImpl;
import com.games.logic.player.PlayerHandlerImpl;
import com.games.rule.game.GameRulesImpl;
import com.games.rule.player.PlayerRulesImpl;
import org.junit.Assert;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameHandlerTest {

    private GameHandlerImpl gameHandlerImpl;

    @Mock
    private PlayerHandlerImpl playerHandler;

    @Mock
    private GameRulesImpl gameRules;

    @Mock
    private PlayerRulesImpl playerRules;

    @Test
    public void should_move_returnGameStateInProgress_whenItIsNotOver() {
        // Given
        gameHandlerImpl = new GameHandlerImpl(playerHandler, gameRules, playerRules, 30, 10);
        GameMove gameMove = new GameMove(10, "Mahdieh", -1, HUMAN);
        when(gameRules.isOver(3)).thenReturn(false);

        // When
        GameStatus gameStatus = gameHandlerImpl.makeMove(gameMove);

        // Then
        Assert.assertEquals(3,gameStatus.getCurrentNumber().intValue());
        Assert.assertEquals(-1,gameStatus.getLastNumberAdded().intValue());
        Assert.assertEquals(IN_PROGRESS, gameStatus.getStatus());
    }

    @Test
    public void should_move_returnGameStateOver_whenItIsOver() {
        // Given
        gameHandlerImpl = new GameHandlerImpl(playerHandler, gameRules, playerRules, 30, 10);
        GameMove gameMove = new GameMove(3, "Mahdieh", 0, COMPUTER);
        when(gameRules.isOver(1)).thenReturn(true);

        // When
        GameStatus gameStatus = gameHandlerImpl.makeMove(gameMove);
        Assert.assertEquals(1,gameStatus.getCurrentNumber().intValue());
        Assert.assertEquals(0,gameStatus.getLastNumberAdded().intValue());
        Assert.assertEquals(OVER, gameStatus.getStatus());
    }

    @Test
    public void should_addNewPlayer_returnGameStateWaitingForPlayer_whenRequiredPlayerNumberIsNotAchieved() {
        // Given
        gameHandlerImpl = new GameHandlerImpl(playerHandler, gameRules, playerRules, 30, 10);
        when(playerRules.isRequiredPlayerNumberAchieved()).thenReturn(false);

        // When
        GameStatus gameStatus = gameHandlerImpl.addNewPlayer("Mahdieh", HUMAN);

        // Then
        Assert.assertNull(gameStatus.getCurrentNumber());
        Assert.assertNull(gameStatus.getLastNumberAdded());
        Assert.assertEquals(WAITING_FOR_PLAYER, gameStatus.getStatus());
    }

    @Test
    public void should_addNewPlayer_returnGameStateInProgress_whenRequiredPlayerNumberIsAchieved() {
        // Given
        gameHandlerImpl = new GameHandlerImpl(playerHandler, gameRules, playerRules, 10, 30);
        when(playerRules.isRequiredPlayerNumberAchieved()).thenReturn(true);

        // When
        GameStatus gameStatus =  gameHandlerImpl.addNewPlayer("Mahdieh", HUMAN);

        // Then
        Assert.assertNotNull(gameStatus.getCurrentNumber());
        Assert.assertNull(gameStatus.getLastNumberAdded());
        Assert.assertEquals(IN_PROGRESS, gameStatus.getStatus());
    }
}
