package app.domain.services;

import app.domain.models.Card.CentralDeck;
import app.domain.services.Map.MapService;
import app.domain.services.base.BasePublisher;

public class GameManagerService extends BasePublisher<Integer> {
    private static GameManagerService _instance;
    private static MapService mapService;
    private static PlayerService playerService;
    private static Integer _gameState;
    private static CentralDeck centralDeck;

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

}