package app.ui.controllers.game.state;

import app.domain.models.game.map.Territory;
import app.ui.views.game.state.AttackPanel;

public class AttackPanelController extends BaseStatePanelController{

    private static AttackPanelController _attackPanelController;

    public static AttackPanelController getInstance() {
        if (_attackPanelController == null) {
            _attackPanelController = new AttackPanelController();
        }
        return _attackPanelController;
    }



    @Override
    public void update(Territory message) {

    }
}
