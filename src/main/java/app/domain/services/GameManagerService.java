package app.domain.services;

import app.domain.models.Card.*;
import app.domain.models.GameMap.Territory;
import app.domain.models.Player.Player;
import app.domain.models.game.GameState;
import app.domain.services.Map.MapService;
import app.domain.services.base.BasePublisher;

import javax.swing.*;
import java.util.ArrayList;

public class GameManagerService extends BasePublisher<GameState> {
    private static GameManagerService _instance;
    private static MapService _mapService;
    private static PlayerService _playerService;
    private static Integer _gameState;
    private static CentralDeck _centralDeck;
    private boolean _isGameFinished;
    private static int playerNumber;

    private GameManagerService() {
        super(GameState.BUILDING_STATE);
    }

    public static GameManagerService getInstance() {
        if (_instance == null) {
            _instance = new GameManagerService();
        }
        return _instance;
    }

    public void initializeGame() {
        updateGameState(getState());
        _isGameFinished = false;
    }

    public void updateGameState(GameState newState) {
        setState(newState);
        notifySubscribers();
    }

    public void handleNextState() {
        GameState currentState = super.getState();
        switch (currentState) {
            case BUILDING_STATE:
                updateGameState(GameState.DISTRIBUTING_STATE);
                break;
            case DISTRIBUTING_STATE:
                updateGameState(GameState.RECEIVING_STATE);
                break;
            case RECEIVING_STATE:
                updateGameState(GameState.ATTACK_STATE);
                break;
            case ATTACK_STATE:
                updateGameState(GameState.FORTIFY_STATE);
                break;
            case FORTIFY_STATE:
                updateGameState(GameState.END_TURN_STATE);
                break;
            case END_TURN_STATE:
                if (_isGameFinished) {
                    updateGameState(GameState.GAME_OVER_STATE);
                    break;
                }
                updateGameState(GameState.RECEIVING_STATE);
                break;
            default:
                break;
        }
    }

    public static void tradeArmyCards(int infantryAmount, int cavalryAmount, int artilleryAmount, int playerId,
            int territoryId) {
        if (_playerService.tradeArmyCards(infantryAmount, cavalryAmount, artilleryAmount, playerId, territoryId)) {

            _centralDeck.addArmyCards(CardType.Infantry, "Infantry card.", new ImageIcon("infantrycard.png"),
                    infantryAmount);
            _centralDeck.addArmyCards(CardType.Cavalry, "Cavalry card.", new ImageIcon("cavalrycard.png"),
                    cavalryAmount);
            _centralDeck.addArmyCards(CardType.Artillery, "Artillery card.",
                    new ImageIcon("artillerycard.png"), artilleryAmount);
        }
    }
    public static void initializeCards(int playerNumber){
        _centralDeck.addArmyCards(CardType.Infantry, "Infantry card", new ImageIcon("infantrycard.png"), playerNumber*3);
        _centralDeck.addArmyCards(CardType.Cavalry, "Cavalry card", new ImageIcon("cavalrycard.png"), playerNumber*2);
        _centralDeck.addArmyCards(CardType.Artillery, "Artillery card.", new ImageIcon("artillerycard.png"), playerNumber);

        ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService.getTerritoryListFromGraph();

        for (Territory t: territoryList){
            _centralDeck.addTerritoryCards("Territory card.", new ImageIcon("territorycard.png"), t.getTerritoryId());
        }

        _centralDeck.shuffle();
    }



    public static void tradeTerritoryCards(String continentName, int playerId){
        if (_playerService.tradeTerritoryCards(continentName, playerId)){

            ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService.getTerritoriesOfContinent(continentName);

            for (Territory t: territoryList){
                _centralDeck.addTerritoryCards("Territory card.", new ImageIcon("territorycard.png"), t.getTerritoryId());
            }
        }
    }

    public static void attack(int attackingPlayerId, int attackedPlayerId, int attackerTerritoryId, int attackedTerritoryId){
        boolean playerCanDrawCard = _playerService.attack(attackingPlayerId, attackedPlayerId,
                attackerTerritoryId, attackedTerritoryId);

        if (playerCanDrawCard){
            Player winningPlayer = PlayerService.getPlayer(attackingPlayerId);
            BaseCard drawnCard = _centralDeck.drawCard(CardType.Army);
            switch (drawnCard.getDescription()) {
                case "Infantry card." -> {
                    InfantryCard iDrawnCard = (InfantryCard) drawnCard;
                    winningPlayer.getPlayerDeck().addArmyCards(CardType.Infantry, iDrawnCard.getDescription(),
                            iDrawnCard.getImageIcon(), iDrawnCard.getValue());
                }
                case "Cavalry card." -> {
                    CavalryCard cDrawnCard = (CavalryCard) drawnCard;
                    winningPlayer.getPlayerDeck().addArmyCards(CardType.Cavalry, cDrawnCard.getDescription(),
                            cDrawnCard.getImageIcon(), cDrawnCard.getValue());
                }
                case "Artillery card." -> {
                    ArtilleryCard aDrawnCard = (ArtilleryCard) drawnCard;
                    winningPlayer.getPlayerDeck().addArmyCards(CardType.Artillery, aDrawnCard.getDescription(),
                            aDrawnCard.getImageIcon(), aDrawnCard.getValue());
                }
                case "Territory card." -> {
                    TerritoryCard tDrawnCard = (TerritoryCard) drawnCard;
                    winningPlayer.getPlayerDeck().addArmyCards(CardType.Territory, tDrawnCard.getDescription(),
                            tDrawnCard.getImageIcon(), tDrawnCard.getTerritoryId());
                }
            }
        }
    }

    public boolean isCentralDeckEmpty(){
        return _centralDeck.isCentralDeckEmpty();
    }

    public void refillCentralDeck(){
        if (isCentralDeckEmpty()){

            ArrayList<Player> players = _playerService.getPlayers();

            for (Player p: players){
                p.getPlayerDeck().emptyDeck();
            }

            initializeCards(players.size());
        }
    }

    public boolean playerHasLost(int playerId){
        if (_playerService.hasPlayerLost(playerId)){
            _playerService.removePlayer(playerId);
            return true;
        }
        return false;
    }

    public boolean playerHasWon(int playerId){
        if (_playerService.hasPlayerWon(playerId)){
            return true;
        }
        return false;
    }

}