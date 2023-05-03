package app.domain.services;

import app.domain.models.Card.CentralDeck;
import app.domain.models.GameMap.Territory;
import app.domain.services.Map.MapService;
import app.domain.services.base.BasePublisher;

import javax.swing.*;
import java.util.ArrayList;

public class GameManagerService extends BasePublisher<Integer> {
    private static GameManagerService _instance;
    private static Integer _gameState;
    private static PlayerService _playerService;
    private static MapService _mapService;
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

    public static void tradeTerritoryCards(String continentName, int playerId){
        if (_playerService.tradeTerritoryCards(continentName, playerId)){

            ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService.getTerritoriesOfContinent(continentName);

            for (Territory t: territoryList){
                _centralDeck.addTerritoryCards("This is a territory card.", new ImageIcon("territorycard.png"), t.getTerritoryId());
            }
        }
    }

}