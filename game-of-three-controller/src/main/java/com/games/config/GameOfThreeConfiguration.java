package com.games.config;

import com.games.logic.game.GameHandler;
import com.games.logic.game.GameHandlerImpl;
import com.games.logic.player.PlayerHandler;
import com.games.logic.player.PlayerHandlerImpl;
import com.games.repository.PlayerRepository;
import com.games.rule.game.GameRules;
import com.games.rule.game.GameRulesImpl;
import com.games.rule.player.PlayerRules;
import com.games.rule.player.PlayerRulesImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameOfThreeConfiguration {

	@Bean
	public GameHandler gameManager(PlayerHandler playerHandler, GameRules gameRules, PlayerRules playerRules,
								   @Value("${gameofthree.random.min-number:2}") int minNumber,
								   @Value("${gameofthree.random.max-number:1000}") int maxNumber) {
		return new GameHandlerImpl(playerHandler, gameRules, playerRules, minNumber, maxNumber);
	}

	@Bean
	public PlayerHandler playerManager(PlayerRepository playerRepository, PlayerRules playerRules) {
		return new PlayerHandlerImpl(playerRepository, playerRules);
	}

	@Bean
	public GameRules gameRules(PlayerRules playerRules, @Value("${gameofthree.game.over-number}") int gameOverAtNumber) {
		return new GameRulesImpl(playerRules,gameOverAtNumber);
	}

	@Bean
	public PlayerRules playerRules(PlayerRepository playerRepository,@Value("${gameofthree.player.required-number}") int requiredPlayerNumberToStart) {
		return new PlayerRulesImpl(playerRepository,requiredPlayerNumberToStart);
	}

}
