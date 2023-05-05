package app.domain.services;

import app.domain.models.Card.CardType;
import app.domain.models.Card.CentralDeck;
import app.domain.models.GameMap.Territory;
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

            _centralDeck.addArmyCards(CardType.Infantry, "This is an infantry card.", new ImageIcon("infantrycard.png"),
                    infantryAmount);
            _centralDeck.addArmyCards(CardType.Cavalry, "This is a cavalry card.", new ImageIcon("cavalrycard.png"),
                    cavalryAmount);
            _centralDeck.addArmyCards(CardType.Artillery, "This is an artillery card.",
                    new ImageIcon("artillerycard.png"), artilleryAmount);
        }
    }
    public static void initializeCards(int playerNumber){
        _centralDeck.addArmyCards(CardType.Infantry, "This is an infantry card", new ImageIcon("infantrycard.png"), playerNumber*3);
        _centralDeck.addArmyCards(CardType.Cavalry, "This is a cavalry card", new ImageIcon("cavalrycard.png"), playerNumber*2);
        _centralDeck.addArmyCards(CardType.Artillery, "This is an artillery card.", new ImageIcon("artillerycard.png"), playerNumber);

        ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService.getTerritoryListFromGraph();

        for (Territory t: territoryList){
            _centralDeck.addTerritoryCards("This is a territory card.", new ImageIcon("territorycard.png"), t.getTerritoryId());
        }
    }



}