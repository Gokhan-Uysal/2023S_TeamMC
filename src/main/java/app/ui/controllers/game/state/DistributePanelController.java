package app.ui.controllers.game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.domain.models.game.map.Territory;
import app.ui.views.game.state.DistributePanel;

public class DistributePanelController extends BaseStatePanelController implements ActionListener {
    private static DistributePanelController _distributePanelController;
    private DistributePanel _distributePanel;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void update(Territory message) {
        _distributePanel.updateSelectedTerritory(message.getName());
        _distributePanel.revalidate();
    }
}
