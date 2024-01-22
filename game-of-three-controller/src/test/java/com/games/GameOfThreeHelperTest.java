package com.games;

import com.games.dto.GameStartDto;
import com.games.utility.GotControllerUtility;
import org.junit.jupiter.api.Test;

import static com.games.domain.GameModeEnum.COMPUTER;
import static com.games.domain.GameModeEnum.HUMAN;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOfThreeHelperTest {

    @Test
    public void should_shouldAddAiPlayer_returnTrue_whenGameModeIsAiAndPlayerNameIsNotAi() {
        // Given
        GameStartDto gameStartDto = new GameStartDto("Mahdieh", COMPUTER);
        String aiPlayerName = "Computer";

        // When
        boolean shouldAddAiPlayer = GotControllerUtility.shouldAddComputerPlayer(gameStartDto, aiPlayerName);

        // Then
        assertThat(shouldAddAiPlayer).isTrue();
    }

    @Test
    public void should_shouldAddAiPlayer_returnFalse_whenGameModeIsAiAndPlayerNameIsAi() {
        // Given
        String aiPlayerName = "Computer";
        GameStartDto gameParticipateRequest = new GameStartDto(aiPlayerName, COMPUTER);

        // When
        boolean shouldAddAiPlayer = GotControllerUtility.shouldAddComputerPlayer(gameParticipateRequest, aiPlayerName);

        // Then
        assertThat(shouldAddAiPlayer).isFalse();
    }

    @Test
    public void should_shouldAddAiPlayer_returnFalse_whenGameModeIsHuman() {
        // Given
        String aiPlayerName = "Computer";
        GameStartDto gameStartDto = new GameStartDto("Mahdieh", HUMAN);

        // When
        boolean shouldAddAiPlayer = GotControllerUtility.shouldAddComputerPlayer(gameStartDto, aiPlayerName);

        // Then
        assertThat(shouldAddAiPlayer).isFalse();
    }
}
