package app.ui.controllers.game.state;

import app.domain.models.game.map.Territory;
import app.ui.views.game.state.FortifyPanel;

public class FortifyPanelController extends BaseStatePanelController {
    private static FortifyPanelController _fortifyPanelController;
    private FortifyPanel _fortifyPanel;

    public FortifyPanel getFortifyPanel() {
        return this._fortifyPanel;
    }

    public static FortifyPanelController getInstance() {
        if (_fortifyPanelController == null) {
            _fortifyPanelController = new FortifyPanelController();
        }
        return _fortifyPanelController;
    }

    private FortifyPanelController() {
        this._fortifyPanel = new FortifyPanel();
    }

    @Override
    public void update(Territory message) {
    }
}
