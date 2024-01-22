package com.games.player;

import com.games.domain.Player;
import com.games.exception.PlayerCannotEnterToGameException;
import com.games.logic.player.PlayerHandlerImpl;
import com.games.repository.PlayerRepository;
import com.games.rule.player.PlayerRulesImpl;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlayerHandlerTest {
    @InjectMocks
    private PlayerHandlerImpl playerHandler;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerRulesImpl playerRules;

    @Test
    public void should_add_savePlayer_whenThePlayerCanBeRegistered() {
        // Given
        Player player = new Player("Mahdieh");

        // When
        playerHandler.add(player);

        // Then
        verify(playerRules).checkThatThePlayerCanBeRegistered("Mahdieh");
        verify(playerRepository).save(player);
    }

    @Test
    public void should_add_throwException_whenThePlayerCannotBeRegistered() {
        // Given
        Player player = new Player("Mahdieh");
        doThrow(PlayerCannotEnterToGameException.class).when(playerRules).checkThatThePlayerCanBeRegistered("Mahdieh");

        // When
        ThrowingRunnable runnable = () -> playerHandler.add(player);

        // Then
        assertThrows(PlayerCannotEnterToGameException.class, runnable);
        verify(playerRepository, never()).save(player);
    }

    @Test
    public void should_removePlayers_deleteAllPlayers() {
        // When
        playerHandler.removePlayers();

        // Then
        verify(playerRepository).deleteAll();
    }
}
