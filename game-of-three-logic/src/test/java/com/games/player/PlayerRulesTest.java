package com.games.player;

import com.games.domain.Player;
import com.games.exception.PlayerCannotEnterToGameException;
import com.games.repository.PlayerRepository;
import com.games.rule.player.PlayerRulesImpl;
import static java.util.Arrays.asList;
import org.junit.Assert;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class PlayerRulesTest {
    private PlayerRulesImpl playerRules;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    public void should_checkThatPlayerNameIsNotUsed_throwException_whenPlayerNameIsAlreadyUsed() {
        playerRules = new PlayerRulesImpl(playerRepository, 2);
        // Given
        when(playerRepository.findAll()).thenReturn(List.of(new Player("Mahdieh")));

        // When
        ThrowingRunnable runnable = () -> playerRules.checkThatPlayerNameIsNotUsed("Mahdieh");

        // Then
        assertThrows("A new player cannot enter to game because Player name is already used.",
                PlayerCannotEnterToGameException.class, runnable);
    }

    @Test
    public void should_checkThatPlayerNameIsNotUsed_doesntThrowException_whenPlayerNameIsNotUsed() {
        playerRules = new PlayerRulesImpl(playerRepository, 2);
        // Given
        when(playerRepository.findAll()).thenReturn(List.of(new Player("Mahdieh")));

        // When
        playerRules.checkThatPlayerNameIsNotUsed("other_name");
    }
    @Test
    public void should_checkThatOtherPlayerIsRequired_throwException_whenNoOtherPlayIsRequired() {
        // Given
        playerRules = new PlayerRulesImpl(playerRepository, 2);
        when(playerRepository.findAll()).thenReturn(asList(new Player("Mahdieh"), new Player("Sarah")));

        // When
        ThrowingRunnable runnable = () -> playerRules.checkThatOtherPlayerIsRequired();

        // Then
        assertThrows("A new player cannot enter to game because Required player number is already achieved.",
                PlayerCannotEnterToGameException.class, runnable);
    }

    @Test
    public void should_checkThatOtherPlayerIsRequired_doesntThrowException_whenOtherPlayIsRequired() {
        // Given
        playerRules = new PlayerRulesImpl(playerRepository, 3);
        when(playerRepository.findAll()).thenReturn(asList(new Player("Mahdieh"), new Player("Sarah")));

        // When
        playerRules.checkThatOtherPlayerIsRequired();
    }

    @Test
    public void should_isRequiredPlayerNumberAchieved_returnTrue_whenRequiredPlayerNumberIsAchieved() {
        // Given
        playerRules = new PlayerRulesImpl(playerRepository, 2);
        when(playerRepository.findAll()).thenReturn(asList(new Player("Mahdieh"), new Player("Sarah")));

        // When
        boolean isRequiredPlayerNumberAchieved = playerRules.isRequiredPlayerNumberAchieved();

        // Then
        Assert.assertTrue(isRequiredPlayerNumberAchieved);
    }

    @Test
    public void should_isRequiredPlayerNumberAchieved_returnFalse_whenRequiredPlayerNumberIsNotAchieved() {
        // Given
        playerRules = new PlayerRulesImpl(playerRepository, 3);
        when(playerRepository.findAll()).thenReturn(asList(new Player("Mahdieh"), new Player("Sarah")));

        // When
        boolean isRequiredPlayerNumberAchieved = playerRules.isRequiredPlayerNumberAchieved();

        // Then
        Assert.assertFalse(isRequiredPlayerNumberAchieved);
    }
}
