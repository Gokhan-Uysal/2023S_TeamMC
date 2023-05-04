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
    private static Integer _gameState;
    private static MapService _mapService;
    private static CentralDeck _centralDeck;
    private static int playerNumber;

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

    public static void initializeCards(int playerNumber){
        for (int i = 0; i < playerNumber*3; i++){
            _centralDeck.addArmyCards(CardType.Infantry, "This is an infantry card", new ImageIcon("infantrycard.png"));
        }
        for (int i = 0; i < playerNumber*2; i++){
            _centralDeck.addArmyCards(CardType.Cavalry, "This is a cavalry card", new ImageIcon("cavalrycard.png"));
        }
        for (int i = 0; i < playerNumber; i++){
            _centralDeck.addArmyCards(CardType.Artillery, "This is an artillery card.", new ImageIcon("artillerycard.png"));
        }

        ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService.getTerritoryListFromGraph();

        for (Territory t: territoryList){
            _centralDeck.addTerritoryCards("This is a territory card.", new ImageIcon("territorycard.png"), t.getTerritoryId());
        }
    }



}