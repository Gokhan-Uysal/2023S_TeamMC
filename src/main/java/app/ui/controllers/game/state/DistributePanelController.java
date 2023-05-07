package app.ui.controllers.game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.state.DistributePanel;

public class DistributePanelController extends BaseStatePanelController implements ActionListener {
    private static DistributePanelController _distributePanelController;
    private DistributePanel _distributePanel;
    private Territory currentSelection;

    public DistributePanel getDistributePanel() {
        return this._distributePanel;
    }

    public static DistributePanelController getInstance() {
        if (_distributePanelController == null) {
            _distributePanelController = new DistributePanelController();
        }
        return _distributePanelController;
    }

    private DistributePanelController() {
        this._distributePanel = new DistributePanel();
        _distributePanel.getConfirmButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentSelection == null) {
            return;
        }
        try {
            GameManagerService.getInstance().placeInfantryToTerritory(currentSelection,
                    PlayerService.getInstance().getCurrentPlayer());
        } catch (Error error) {
            new ErrorAlertPanel(_distributePanel.getRootFrame(_distributePanel), error.getMessage());
        }
    }

    @Override
    public void update(Territory message) {
        currentSelection = message;
        _distributePanel.updateSelectedTerritory(message.getName());
        _distributePanel.revalidate();
    }
}
