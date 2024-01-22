package com.games.logic.player;

import com.games.domain.Player;
import com.games.repository.PlayerRepository;
import com.games.rule.player.PlayerRules;

public class PlayerHandlerImpl implements PlayerHandler {

	private final PlayerRepository playerRepository;

	private final PlayerRules playerRules;

	public PlayerHandlerImpl(PlayerRepository playerRepository, PlayerRules playerRules) {
		this.playerRepository = playerRepository;
		this.playerRules = playerRules;
	}

	@Override
	public void add(Player player) {
		playerRules.checkThatThePlayerCanBeRegistered(player.getName());

		playerRepository.save(player);
	}

	@Override
	public void removePlayers() {
		playerRepository.deleteAll();
	}

	public boolean isAPlayerWaiting() {
		return playerRepository.findAll().size() == 1;
	}

}
