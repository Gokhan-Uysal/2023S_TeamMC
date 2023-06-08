package app.ui.controllers.game.state;

import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.state.ReceivePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceivePanelController extends BaseStatePanelController implements ActionListener {
    private static ReceivePanelController _receivePanelController;
    private ReceivePanel _receivePanel;
    private Territory _currentSelection = null;

    private ReceivePanelController(){
        this._receivePanel = new ReceivePanel();
        _receivePanel.getPlaceUnitsButton().addActionListener(this);
        _receivePanel.setReceivedUnitNumber(GameManagerService.getInstance().receivedUnitNumber());
    }

    public static ReceivePanelController getInstance(){
        if (_receivePanelController == null){
            _receivePanelController = new ReceivePanelController();
        }
        return _receivePanelController;
    }

    public ReceivePanel getReceivePanel(){
        return this._receivePanel;
    }

    @Override
    public void update(Territory message) {
        _currentSelection = message;
        _receivePanel.updateSelectedTerritory(message.getName());
        _receivePanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (_currentSelection == null){
            return;
        }
        try{
            GameManagerService.getInstance().receiveUnits(_currentSelection);
            _receivePanel.setButtonActive(false);
        }
        catch (Error error){
            new ErrorAlertPanel(_receivePanel.getRootFrame(_receivePanel), error.getMessage());
        }
    }
}
