package com.games.logic.player;


import com.games.domain.Player;

public interface PlayerHandler {

	void add(Player player);

	void removePlayers();

	boolean isAPlayerWaiting();
}
