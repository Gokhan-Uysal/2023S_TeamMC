package app.domain.services;

public class GameManagerService extends BasePublisher {
    private static GameManagerService _instance;

    private GameManagerService() {
    }

    public static GameManagerService getInstance() {
        if (_instance == null) {
            GameManagerService._instance = new GameManagerService();
        }
        return GameManagerService._instance;
    }
}