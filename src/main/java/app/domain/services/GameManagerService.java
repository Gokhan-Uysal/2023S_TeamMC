package app.domain.services;

public class GameManagerService extends BasePublisher<Integer> {
    private static GameManagerService _instance;
    private static Integer _gameState;

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