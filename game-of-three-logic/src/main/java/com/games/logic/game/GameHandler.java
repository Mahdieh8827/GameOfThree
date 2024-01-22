package com.games.logic.game;


import com.games.domain.GameModeEnum;
import com.games.domain.GameMove;
import com.games.domain.GameStatus;

public interface GameHandler {
	GameStatus makeMove(GameMove gameMove);
	GameStatus addNewPlayer(String player, GameModeEnum gameMode);

}
