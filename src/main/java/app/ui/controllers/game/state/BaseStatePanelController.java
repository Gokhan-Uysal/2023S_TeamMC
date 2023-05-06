package app.ui.controllers.game.state;

import app.domain.models.game.map.Territory;
import app.domain.services.base.ISubscriber;

public class BaseStatePanelController implements ISubscriber<Territory> {
    private static BaseStatePanelController _instance;

    public static synchronized BaseStatePanelController getInstance() {
        if (_instance == null) {
            _instance = new BaseStatePanelController();
        }
        return _instance;
    }

    @Override
    public void update(Territory message) {
        System.out.println(message);
    }
}
