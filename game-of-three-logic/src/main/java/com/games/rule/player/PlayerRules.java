package com.games.rule.player;

public interface PlayerRules {

     void checkThatPlayerNameIsNotUsed(String playerName) ;

     void checkThatOtherPlayerIsRequired() ;

     boolean isRequiredPlayerNumberAchieved() ;

     void checkThatThePlayerCanBeRegistered(String playerName) ;
}
