package com.games.rule.player;

import com.games.domain.Player;
import com.games.exception.PlayerCannotEnterToGameException;
import com.games.repository.PlayerRepository;

import java.util.List;
import java.util.function.Predicate;

import static com.games.rule.DefinedRules.REQUIRED_PLAYER_NAME_USED;
import static com.games.rule.DefinedRules.REQUIRED_PLAYER_NUMBER_ACHIEVED;
import static org.springframework.util.CollectionUtils.isEmpty;

public class PlayerRulesImpl implements PlayerRules{
    private final PlayerRepository playerRepository;
    private final int requiredPlayerNumberToStart;

    public PlayerRulesImpl(PlayerRepository playerRepository, int requiredPlayerNumberToStart) {
        this.playerRepository = playerRepository;
        this.requiredPlayerNumberToStart = requiredPlayerNumberToStart;
    }

    public void checkThatPlayerNameIsNotUsed(String playerName) {
        if (playerWithSameNameExists(playerName)) {
            throw new PlayerCannotEnterToGameException(REQUIRED_PLAYER_NAME_USED.getMessage());
        }
    }

    private boolean playerWithSameNameExists(String playerName) {
        List<Player> playerAlreadyRegistered = playerRepository.findAll();
        return !isEmpty(playerAlreadyRegistered)
                && playerAlreadyRegistered.stream().anyMatch(playerWithSameName(playerName));//TODO Mahdieh
    }

    private Predicate<Player> playerWithSameName(String playerName) {
        return player -> player.getName().equals(playerName);
    }

    public void checkThatOtherPlayerIsRequired() {
        if (isRequiredPlayerNumberAchieved()) {
            throw new PlayerCannotEnterToGameException(REQUIRED_PLAYER_NUMBER_ACHIEVED.getMessage());
        }
    }

    public boolean isRequiredPlayerNumberAchieved() {
        List<Player> playerAlreadyRegistered = playerRepository.findAll();
        return !isEmpty(playerAlreadyRegistered) && playerAlreadyRegistered.size() >= requiredPlayerNumberToStart;
    }


    public void checkThatThePlayerCanBeRegistered(String playerName) {
       checkThatOtherPlayerIsRequired();

        checkThatPlayerNameIsNotUsed(playerName);
    }
}
