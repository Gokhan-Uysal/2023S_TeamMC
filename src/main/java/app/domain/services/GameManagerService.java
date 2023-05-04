package app.domain.services;

import app.domain.models.Card.CardType;
import app.domain.models.Card.CentralDeck;
import app.domain.models.GameMap.Territory;
import app.domain.services.Map.MapService;
import app.domain.services.base.BasePublisher;

import javax.swing.*;
import java.util.ArrayList;

public class GameManagerService extends BasePublisher<Integer> {
    private static GameManagerService _instance;
    private static MapService _mapService;
    private static PlayerService _playerService;
    private static Integer _gameState;
    private static CentralDeck _centralDeck;

    private GameManagerService() {
        super(_gameState);
        setState(0);
    }

    public static GameManagerService getInstance() {
        if (_instance == null) {
            GameManagerService._instance = new GameManagerService();
        }
        return GameManagerService._instance;
    }

    public static void tradeArmyCards(int infantryAmount, int cavalryAmount, int artilleryAmount, int playerId, int territoryId){
        if (_playerService.tradeArmyCards(infantryAmount, cavalryAmount, artilleryAmount, playerId, territoryId)){

            _centralDeck.addArmyCards(CardType.Infantry, "This is an infantry card.", new ImageIcon("infantrycard.png"), infantryAmount);
            _centralDeck.addArmyCards(CardType.Cavalry, "This is a cavalry card.", new ImageIcon("cavalrycard.png"), cavalryAmount);
            _centralDeck.addArmyCards(CardType.Artillery, "This is an artillery card.", new ImageIcon("artillerycard.png"), artilleryAmount);
        }
    }
}